package me.soushin.sunshine.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uber.autodispose.AutoDispose
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.base.AutoDisposeFragmentKotlin
import me.soushin.sunshine.ui.base.error.ErrorAction
import me.soushin.sunshine.ui.base.error.ErrorStore
import me.soushin.sunshine.ui.base.forecasts.ForecastsAction
import me.soushin.sunshine.ui.base.forecasts.ForecastsStore
import me.soushin.sunshine.ui.base.settings.SettingsStore
import me.soushin.sunshine.ui.home.binder.CurrentWeatherViewBinder
import me.soushin.sunshine.ui.home.binder.HomeViewType
import me.soushin.sunshine.ui.util.RecyclerAdapter
import me.soushin.sunshine.ui.util.binder.ForecastCityBinder
import me.soushin.sunshine.ui.util.binder.ForecastIconAuthorBinder
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates


class HomeFragment : AutoDisposeFragmentKotlin() {

    @Inject lateinit var forecastsAction: ForecastsAction
    @Inject lateinit var forecastsStore: ForecastsStore
    @Inject lateinit var settingsStore: SettingsStore
    @Inject lateinit var errorAction: ErrorAction
    @Inject lateinit var errorStore: ErrorStore

    private var recycleView: RecyclerView by Delegates.notNull()
    private var swipeRefreshLayout: SwipeRefreshLayout by Delegates.notNull()

    private val adapter = RecyclerAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false) ?: return null
        recycleView = view.findViewById(R.id.recyclerView)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return

        forecastsStore.currentWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(AutoDispose.autoDisposable(this))
                .subscribe { currentWeather ->

                    swipeRefreshLayout.isRefreshing = false

                    listOf(
                            CurrentWeatherViewBinder(context, HomeViewType.WEATHER, currentWeather),
                            ForecastIconAuthorBinder(context, HomeViewType.ICON_AUTHOR)
                    ).let {
                        adapter.replaceAll(it)
                    }
                }

        savedInstanceState ?: request()

        swipeRefreshLayout.setOnRefreshListener { request() }
    }

    private fun request() {
        settingsStore.zipCode()
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(AutoDispose.autoDisposable(this))
                .subscribe {
                    if (it.isNotBlank()) {
                        forecastsAction.getCurrentWeatherByZipcode(it)
                    } else {
                        errorAction.onError("You must to set ZipCode.")
                    }
                }
    }

    override fun onResume() {
        super.onResume()

        errorStore.errors()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(AutoDispose.autoDisposable(this))
                .subscribe { error ->
                    swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
                }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
