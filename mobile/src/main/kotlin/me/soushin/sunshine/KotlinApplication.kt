package me.soushin.sunshine

import dagger.android.support.DaggerApplication

class KotlinApplication : DaggerApplication() {

    override fun applicationInjector() = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
    }
}
