package me.soushin.sunshine.data.repository

import me.soushin.sunshine.data.api.OpenWeatherMapService


class OpenWeatherMapRepository(val openWeatherMapService: OpenWeatherMapService) {

    fun findForecastByDaily() = openWeatherMapService.findForecastByDaily()

}