package me.soushin.sunshine.ui.base.settings

import me.soushin.sunshine.data.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsAction @Inject constructor(private val settingsRepository: SettingsRepository) {

    fun updateZipCode(zipCode: String) = settingsRepository.updateZipCode(zipCode)
}
