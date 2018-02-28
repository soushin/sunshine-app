package me.soushin.sunshine.data.repository

import me.soushin.sunshine.data.api.OpenWeatherMapClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenWeatherMapRepository @Inject constructor(private val openWeatherMapClient: OpenWeatherMapClient) {
    fun findForecastByDaily() = openWeatherMapClient.findForecastByDaily()
}