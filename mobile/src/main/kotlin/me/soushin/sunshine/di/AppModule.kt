package me.soushin.sunshine.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.soushin.sunshine.KotlinApplication
import me.soushin.sunshine.di.data.ApiModule
import me.soushin.sunshine.di.data.DataModule
import me.soushin.sunshine.ui.base.error.ErrorDispatcher
import me.soushin.sunshine.ui.base.forecasts.ForecastsDispatcher
import javax.inject.Singleton

@Module(includes = [ApiModule::class, DataModule::class])
internal object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideApplication(app: KotlinApplication): Context = app

    @Provides
    @Singleton
    @JvmStatic
    fun provideErrorDispatcher() = ErrorDispatcher()
}
