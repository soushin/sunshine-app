package me.soushin.sunshine.ui.forecasts

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_NO_YEAR
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.soushin.sunshine.R
import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class ForecastsFragment : Fragment() {

    @Inject
    lateinit var openWeatherMapRepository: OpenWeatherMapRepository

    private var listView: ListView by Delegates.notNull()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.forcasts_fragment, container, false) ?: return null
        listView = view.findViewById<ListView>(R.id.list_view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        openWeatherMapRepository.findForecastByDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { forecasts ->
                    listView.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1,
                            forecasts.list.map {
                                "%s - %s %s/%s".format(
                                        DateUtils.formatDateTime(activity, it.dt * 1000L, FORMAT_NO_YEAR),
                                        it.weather.get(0).main, it.temp.min, it.temp.max)
                            })
                }
    }

    companion object {
        fun newInstance() = ForecastsFragment()
    }
}
