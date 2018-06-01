package me.soushin.sunshine.ui.forecast

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
import com.uber.autodispose.AutoDispose.autoDisposable
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import me.soushin.sunshine.R
import me.soushin.sunshine.data.api.dto.Forecast
import me.soushin.sunshine.ui.base.AutoDisposeFragmentKotlin
import me.soushin.sunshine.ui.base.error.ErrorAction
import me.soushin.sunshine.ui.base.error.ErrorStore
import me.soushin.sunshine.ui.base.forecasts.ForecastsAction
import me.soushin.sunshine.ui.base.forecasts.ForecastsStore
import me.soushin.sunshine.ui.base.settings.SettingsStore
import me.soushin.sunshine.ui.util.binder.ForecastCityBinder
import me.soushin.sunshine.ui.util.binder.ForecastIconAuthorBinder
import me.soushin.sunshine.ui.forecast.binder.ForecastViewBinder
import me.soushin.sunshine.ui.forecast.binder.ForecastViewType
import me.soushin.sunshine.ui.util.RecyclerAdapter
import me.soushin.sunshine.ui.util.binder.IntentDividerBinder
import me.soushin.sunshine.util.extention.replaceFragment
import javax.inject.Inject
import kotlin.properties.Delegates

class ForecastsFragment : AutoDisposeFragmentKotlin() {

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
        val view = inflater.inflate(R.layout.forcasts_fragment, container, false) ?: return null
        recycleView = view.findViewById(R.id.recyclerView)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return

        forecastsStore.forecasts()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(autoDisposable(this))
                .subscribe { forecasts ->

                    swipeRefreshLayout.isRefreshing = false

                    val onClick: (Forecast) -> Unit = { forecast: Forecast ->
                        fragmentManager?.replaceFragment(R.id.mainLayout) {
                            Bundle().apply {
                                putParcelable(ForecastFragment.KEY_FORECAST, forecast)

                            }.let {
                                ForecastFragment.newInstance().apply {
                                    arguments = it
                                }
                            }
                        }
                    }

                    val items = forecasts.list.map {
                        listOf(ForecastViewBinder(context, ForecastViewType.FORECAST, it, onClick),
                                IntentDividerBinder(context, ForecastViewType.DIVIDER, R.dimen.intent_divider_margin)
                        )
                    }.flatten().toMutableList().also {
                        it.add(0, ForecastCityBinder(context, ForecastViewType.CITY, "%s/%s".format(forecasts.city.name, forecasts.city.country)))
                        it.add(it.size, ForecastIconAuthorBinder(context, ForecastViewType.ICON_AUTHOR))
                    }.toList()

                    adapter.replaceAll(items)
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
                        forecastsAction.findByDaily(it)
                    } else {
                        errorAction.onError("You must to set ZipCode.")
                    }
                }
    }

    override fun onResume() {
        super.onResume()

        errorStore.errors()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(autoDisposable(this))
                .subscribe { error ->
                    swipeRefreshLayout.isRefreshing = false
                    Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
                }
    }

    companion object {
        fun newInstance() = ForecastsFragment()
    }
}
