package me.soushin.sunshine.di

import android.app.Application
import me.soushin.sunshine.data.repository.OpenWeatherMapRepository
import me.soushin.sunshine.data.repository.SettingsRepository
import me.soushin.sunshine.ui.base.error.ErrorAction
import me.soushin.sunshine.ui.base.error.ErrorStore
import me.soushin.sunshine.ui.base.forecasts.ForecastsAction
import me.soushin.sunshine.ui.base.forecasts.ForecastsStore
import me.soushin.sunshine.ui.base.settings.SettingsAction
import me.soushin.sunshine.ui.base.settings.SettingsStore

interface ApplicationComponentModules {

    fun application(): Application

    // repository
    fun openWeatherMapRepository(): OpenWeatherMapRepository
    fun settingsRepository(): SettingsRepository

    // flux: errors
    fun errorAction(): ErrorAction
    fun errorStore(): ErrorStore

    // flux: settings
    fun settingsAction(): SettingsAction
    fun settingsStore(): SettingsStore
}
