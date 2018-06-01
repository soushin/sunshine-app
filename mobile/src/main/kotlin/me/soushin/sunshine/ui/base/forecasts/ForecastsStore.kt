package me.soushin.sunshine.ui.base.forecasts

class ForecastsStore(private val forecastsDispatcher: ForecastsDispatcher) {
    fun currentWeather() = forecastsDispatcher.currentWeatherProcessor
    fun forecasts() = forecastsDispatcher.forecastsProcessor
}
