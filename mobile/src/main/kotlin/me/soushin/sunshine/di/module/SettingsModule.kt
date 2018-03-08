package me.soushin.sunshine.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.soushin.sunshine.ui.settings.SettingsFragment

@Module
internal abstract class SettingsModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): SettingsFragment

}
