package me.soushin.sunshine.ui.forecasts.binder

import android.content.Context
import android.support.design.chip.Chip
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotterknife.bindView
import me.soushin.sunshine.R
import me.soushin.sunshine.data.api.dto.Forecast

class ForecastChipView(context: Context, attrs: AttributeSet?,
                       defStyleAttr: Int) : RelativeLayout(context, attrs, defStyleAttr) {

    val chip: Chip by bindView(R.id.chip)

    init {
        LayoutInflater.from(context).inflate(R.layout.forecast_chip_view, this, true)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    fun bindForecast(forecast: Forecast) {
        forecast.let {
            chip.chipText = DateUtils.formatDateTime(context, forecast.dt * 1000L, DateUtils.FORMAT_NO_YEAR)
            chip.setChipIconResource(it.iconDrawableRes())
        }
    }
}
