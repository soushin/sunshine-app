package me.soushin.sunshine.ui.base.forecasts

import io.reactivex.schedulers.Schedulers
import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import me.soushin.sunshine.ui.base.error.Err
import me.soushin.sunshine.ui.base.error.ErrorDispatcher
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastsAction @Inject constructor(private val forecastsDispatcher: ForecastsDispatcher,
                                          private val errorDispatcher: ErrorDispatcher,
                                          private val openWeatherMapRepository: OpenWeatherMapRepository) {
    fun findByDaily(query: String) {
        openWeatherMapRepository.findForecastByDaily(query)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    forecastsDispatcher.forecastsProcessor.onNext(it)
                }, {
                    errorDispatcher.onError(Err(it.message))
                })
    }
}