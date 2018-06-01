package me.soushin.sunshine.ui.base.forecasts

import io.reactivex.processors.PublishProcessor
import me.soushin.sunshine.data.api.dto.CurrentWeather
import me.soushin.sunshine.data.api.dto.Forecasts

class ForecastsDispatcher {
    val currentWeatherProcessor = PublishProcessor.create<CurrentWeather>()
    val forecastsProcessor = PublishProcessor.create<Forecasts>()
}
