package me.soushin.sunshine.data.repository

import android.app.Application
import android.content.Context
import com.f2prateek.rx.preferences2.RxSharedPreferences
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import me.soushin.sunshine.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(private val application: Application) {

    private val SETTING_NAME = "setting"
    private val ZIP_CODE_NAME = "zip_code"

    fun getZipCode() = getRxSharedPreferences(SETTING_NAME).getString(ZIP_CODE_NAME)
    fun updateZipCode(zipCode: String) = getZipCode().set(zipCode)

    private fun getRxSharedPreferences(name: String) = RxSharedPreferences.create(
            application.getSharedPreferences(BuildConfig.APPLICATION_ID + '.' + name, Context.MODE_PRIVATE))
}
