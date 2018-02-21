package me.soushin.sunshine.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.soushin.sunshine.ui.forecasts.ForecastsFragment

@Module
internal abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ForecastsFragment

}
