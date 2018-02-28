package me.soushin.sunshine.ui.base.error

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorStore @Inject constructor(private val errorDispatcher: ErrorDispatcher) {
    fun errors() = errorDispatcher.errors
}
