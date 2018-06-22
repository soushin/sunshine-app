package me.soushin.sunshine.ui.forecast

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import dagger.android.support.AndroidSupportInjection
import me.soushin.sunshine.R
import me.soushin.sunshine.data.api.dto.Forecast
import me.soushin.sunshine.ui.base.AutoDisposeFragmentKotlin
import me.soushin.sunshine.ui.base.error.ErrorAction
import me.soushin.sunshine.ui.base.error.ErrorStore
import me.soushin.sunshine.ui.forecast.binder.ForecastViewBinder
import me.soushin.sunshine.ui.forecast.binder.ForecastViewType
import me.soushin.sunshine.ui.util.RecyclerAdapter
import me.soushin.sunshine.ui.util.binder.ForecastIconAuthorBinder
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates


class ForecastFragment : AutoDisposeFragmentKotlin() {

    @Inject lateinit var errorAction: ErrorAction
    @Inject lateinit var errorStore: ErrorStore
    @Inject lateinit var gson: Gson

    companion object {
        fun newInstance() = ForecastFragment()
    }

    private val forecast: Forecast by lazy {
        ForecastFragmentArgs.fromBundle(arguments).let {
            gson.fromJson(it.forecast, Forecast::class.java)
        }
    }

    private var recycleView: RecyclerView by Delegates.notNull()
    private val adapter = RecyclerAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.forcast_fragment, container, false) ?: return null
        recycleView = view.findViewById(R.id.recyclerView)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = context ?: return

        listOf(ForecastViewBinder(context, ForecastViewType.FORECAST, forecast),
                ForecastIconAuthorBinder(context, ForecastViewType.ICON_AUTHOR)).let {
            adapter.replaceAll(it)
        }
    }
}

