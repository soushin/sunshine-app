package me.soushin.sunshine.di.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.soushin.sunshine.di.module.MainModule
import me.soushin.sunshine.ui.main.MainActivity

@Module
internal abstract class UiModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}