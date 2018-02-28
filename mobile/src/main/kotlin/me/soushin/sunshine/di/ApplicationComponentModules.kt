package me.soushin.sunshine.di

import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import me.soushin.sunshine.ui.base.error.ErrorStore
import me.soushin.sunshine.ui.base.forecasts.ForecastsAction
import me.soushin.sunshine.ui.base.forecasts.ForecastsStore

interface ApplicationComponentModules {

    fun openWeatherMapRepository(): OpenWeatherMapRepository

    // flux: errors
    fun errorStore(): ErrorStore

    // flux: forecasts
    fun forecastsAction(): ForecastsAction
    fun forecastsStore(): ForecastsStore
}