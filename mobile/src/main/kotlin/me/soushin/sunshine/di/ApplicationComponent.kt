package me.soushin.sunshine.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.soushin.sunshine.KotlinApplication
import me.soushin.sunshine.di.data.ApiModule
import me.soushin.sunshine.di.data.DataModule
import me.soushin.sunshine.di.ui.UiModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        DataModule::class,
        UiModule::class))
interface ApplicationComponent : AndroidInjector<KotlinApplication>, ApplicationComponentModules {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: KotlinApplication): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(application: KotlinApplication)
}
