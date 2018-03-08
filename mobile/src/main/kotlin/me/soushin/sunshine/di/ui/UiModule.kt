package me.soushin.sunshine.di.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.soushin.sunshine.di.module.MainModule
import me.soushin.sunshine.di.module.SettingsModule
import me.soushin.sunshine.ui.main.MainActivity
import me.soushin.sunshine.ui.settings.SettingsActivity

@Module
internal abstract class UiModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SettingsModule::class])
    internal abstract fun contributeSettingsActivity(): SettingsActivity
}