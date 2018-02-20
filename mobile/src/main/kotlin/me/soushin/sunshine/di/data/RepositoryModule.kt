package me.soushin.sunshine.di.data

import dagger.Module
import dagger.Provides
import me.soushin.sunshine.data.api.OpenWeatherMapService
import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import javax.inject.Singleton


@Module
internal object RepositoryModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideOpenWeatherMapRepository(openWeatherMapService: OpenWeatherMapService) =
            OpenWeatherMapRepository(openWeatherMapService)
}