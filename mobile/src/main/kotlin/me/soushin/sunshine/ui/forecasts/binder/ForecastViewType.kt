package me.soushin.sunshine.ui.forecasts.binder

import me.soushin.sunshine.ui.util.ViewType

enum class ForecastViewType : ViewType {
    CITY,
    FORECAST,
    ICON_AUTHOR,
    DIVIDER;

    override fun viewType(): Int = ordinal
}
