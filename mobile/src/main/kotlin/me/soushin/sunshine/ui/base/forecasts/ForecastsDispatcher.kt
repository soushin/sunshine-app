package me.soushin.sunshine.ui.base.forecasts

import io.reactivex.processors.PublishProcessor
import me.soushin.sunshine.data.api.dto.Forecasts
import me.soushin.sunshine.ui.base.error.ErrorDispatcher

class ForecastsDispatcher {
    val forecastsProcessor = PublishProcessor.create<Forecasts>()
}