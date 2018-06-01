package me.soushin.sunshine.di.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.soushin.sunshine.di.PerUiScope
import me.soushin.sunshine.di.module.ForecastsModule
import me.soushin.sunshine.ui.forecast.ForecastFragment
import me.soushin.sunshine.ui.home.HomeFragment
import me.soushin.sunshine.ui.forecast.ForecastsFragment
import me.soushin.sunshine.ui.main.MainActivity
import me.soushin.sunshine.ui.settings.SettingsActivity
import me.soushin.sunshine.ui.settings.SettingsFragment

@Module
abstract class UiBuilder {

//    @PerUiScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

//    @PerUiScope
    @ContributesAndroidInjector
    internal abstract fun contributeSettingsActivity(): SettingsActivity

    @PerUiScope
    @ContributesAndroidInjector(modules = [ForecastsModule::class])
    internal abstract fun contributeHomeFragment(): HomeFragment

    @PerUiScope
    @ContributesAndroidInjector
    internal abstract fun contributeForecastFragment(): ForecastFragment

    @PerUiScope
    @ContributesAndroidInjector(modules = [ForecastsModule::class])
    internal abstract fun contributeForecastsFragment(): ForecastsFragment

    @PerUiScope
    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): SettingsFragment
}
