package me.soushin.sunshine.data.repository

import io.reactivex.Single
import me.soushin.sunshine.data.api.OpenWeatherMapClient
import me.soushin.sunshine.data.api.dto.Forecasts
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenWeatherMapRepository @Inject constructor(private val openWeatherMapClient: OpenWeatherMapClient) {
    fun findForecastByDaily(query: String) = openWeatherMapClient.findForecastByDaily(query = query)
}