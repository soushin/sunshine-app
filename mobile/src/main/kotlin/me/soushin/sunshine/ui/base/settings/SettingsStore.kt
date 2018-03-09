package me.soushin.sunshine.ui.base.settings

import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers
import me.soushin.sunshine.data.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsStore @Inject constructor(private val settingsRepository: SettingsRepository) {
    fun zipCode() = settingsRepository.getZipCode()
            .asObservable()
            .subscribeOn(Schedulers.io())
            .toFlowable(BackpressureStrategy.LATEST)
}
