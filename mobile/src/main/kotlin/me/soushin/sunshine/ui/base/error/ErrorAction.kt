package me.soushin.sunshine.ui.base.error

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorAction @Inject constructor(private val errorDispatcher: ErrorDispatcher) {
    fun onError(message: String) = errorDispatcher.onError(Err(message))
}
