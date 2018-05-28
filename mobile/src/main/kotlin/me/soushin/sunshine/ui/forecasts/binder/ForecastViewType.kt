package me.soushin.sunshine.ui.forecasts.binder

import me.soushin.sunshine.ui.util.ViewType

enum class ForecastViewType : ViewType {
    FORECAST,
    DIVIDER;

    override fun viewType(): Int = ordinal
}
