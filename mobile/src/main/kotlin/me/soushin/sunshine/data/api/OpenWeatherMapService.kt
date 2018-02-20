package me.soushin.sunshine.data.api

import io.reactivex.Observable
import me.soushin.sunshine.data.api.dto.Forecasts
import retrofit2.http.GET

interface OpenWeatherMapService {

    @GET("/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7&APPID=8fec1d6b295722570a4bc2736d6be386")
    fun findForecastByDaily(): Observable<Forecasts>
}