package me.soushin.sunshine

import dagger.android.support.DaggerApplication
import me.soushin.sunshine.di.DaggerApplicationComponent
import timber.log.Timber

class KotlinApplication : DaggerApplication() {

    override fun applicationInjector() = DaggerApplicationComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
