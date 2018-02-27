package me.soushin.sunshine.ui.forecasts

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_NO_YEAR
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.uber.autodispose.AutoDispose.autoDisposable
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.base.AutoDisposeFragmentKotlin
import me.soushin.sunshine.ui.base.error.ErrorStore
import me.soushin.sunshine.ui.base.forecasts.ForecastsAction
import me.soushin.sunshine.ui.base.forecasts.ForecastsStore
import javax.inject.Inject
import kotlin.properties.Delegates

class ForecastsFragment : AutoDisposeFragmentKotlin() {

    @Inject lateinit var forecastsAction: ForecastsAction
    @Inject lateinit var forecastsStore: ForecastsStore
    @Inject lateinit var errorStore: ErrorStore

    private var listView: ListView by Delegates.notNull()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.forcasts_fragment, container, false) ?: return null
        listView = view.findViewById<ListView>(R.id.list_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forecastsStore.forecasts()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(autoDisposable(this))
                .subscribe { forecasts ->
                    listView.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1,
                            forecasts.list.map {
                                "%s - %s %s/%s".format(
                                        DateUtils.formatDateTime(activity, it.dt * 1000L, FORMAT_NO_YEAR),
                                        it.weather.get(0).main, it.temp.min, it.temp.max)
                            })
                }

        savedInstanceState ?: forecastsAction.findByDaily()
    }

    override fun onResume() {
        super.onResume()

        errorStore.errors()
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(autoDisposable(this))
                .subscribe { error ->
                    Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
                }
    }

    companion object {
        fun newInstance() = ForecastsFragment()
    }
}
