package me.soushin.sunshine.data.api

import io.reactivex.Single
import me.soushin.sunshine.data.api.dto.CurrentWeather
import me.soushin.sunshine.data.api.dto.Forecasts
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapClient {

    @GET("/data/2.5/weather?mode=json&units=metric&APPID=8fec1d6b295722570a4bc2736d6be386")
    fun getCurrentWeatherByZipCode(@Query("zip") query: String = "94043"): Single<CurrentWeather>

    @GET("/data/2.5/forecast/daily?mode=json&units=metric&APPID=8fec1d6b295722570a4bc2736d6be386")
    fun findForecastByDaily(@Query("q") query: String = "94043", @Query("cnt") count: Int = 7): Single<Forecasts>
}
