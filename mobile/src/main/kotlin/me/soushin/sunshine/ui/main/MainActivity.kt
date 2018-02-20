package me.soushin.sunshine.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_NO_YEAR
import android.widget.ArrayAdapter
import android.widget.ListView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import me.soushin.sunshine.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var openWeatherMapRepository: OpenWeatherMapRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openWeatherMapRepository.findForecastByDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { forecasts ->
                    findViewById<ListView>(R.id.listview).let { view ->
                        view.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                                forecasts.list.map {
                                    "%s - %s %s/%s".format(
                                    DateUtils.formatDateTime(this, it.dt * 1000L, FORMAT_NO_YEAR),
                                            it.weather.get(0).main, it.temp.min, it.temp.max)
                                })
                    }
                }
    }
}
