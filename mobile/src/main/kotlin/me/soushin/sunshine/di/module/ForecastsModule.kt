package me.soushin.sunshine.di.module

import dagger.Module
import dagger.Provides
import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import me.soushin.sunshine.di.PerUiScope
import me.soushin.sunshine.ui.base.error.ErrorDispatcher
import me.soushin.sunshine.ui.base.forecasts.ForecastsAction
import me.soushin.sunshine.ui.base.forecasts.ForecastsDispatcher
import me.soushin.sunshine.ui.base.forecasts.ForecastsStore

@Module
class ForecastsModule {

    @Provides
    @PerUiScope
    fun provideForecastsDispatcher(): ForecastsDispatcher = ForecastsDispatcher()

    @Provides
    @PerUiScope
    fun provideForecastsAction(dispatcher: ForecastsDispatcher, errorDispatcher: ErrorDispatcher,
                               openWeatherMapRepository: OpenWeatherMapRepository): ForecastsAction =
            ForecastsAction(dispatcher, errorDispatcher, openWeatherMapRepository)

    @Provides
    @PerUiScope
    fun provideForecastStore(dispatcher: ForecastsDispatcher): ForecastsStore = ForecastsStore(dispatcher)
}
