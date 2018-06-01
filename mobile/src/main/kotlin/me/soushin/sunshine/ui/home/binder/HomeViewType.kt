package me.soushin.sunshine.ui.home.binder

import me.soushin.sunshine.ui.util.ViewType

enum class HomeViewType : ViewType {
    CITY,
    WEATHER,
    ICON_AUTHOR,
    DIVIDER;

    override fun viewType(): Int = ordinal
}
