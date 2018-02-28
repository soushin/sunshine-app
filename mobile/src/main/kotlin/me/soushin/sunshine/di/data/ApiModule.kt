package me.soushin.sunshine.di.data

import dagger.Module
import dagger.Provides
import me.soushin.sunshine.data.api.OpenWeatherMapClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal object ApiModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideOpenWeatherMapClient(retrofit: Retrofit) = retrofit.create(OpenWeatherMapClient::class.java)
}
