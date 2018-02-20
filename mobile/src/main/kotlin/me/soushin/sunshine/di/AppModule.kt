package me.soushin.sunshine.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.soushin.sunshine.KotlinApplication
import javax.inject.Singleton

@Module
internal object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideApplication(app : KotlinApplication): Context = app
}