package me.soushin.sunshine.ui.base.forecasts

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastsStore @Inject constructor(private val forecastsDispatcher: ForecastsDispatcher) {
    fun forecasts() = forecastsDispatcher.forecastsProcessor
}