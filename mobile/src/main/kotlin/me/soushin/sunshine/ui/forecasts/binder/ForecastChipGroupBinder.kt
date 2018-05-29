package me.soushin.sunshine.ui.forecasts.binder

import android.content.Context
import android.support.design.chip.ChipGroup
import android.support.v7.widget.RecyclerView
import android.view.View
import kotterknife.bindView
import me.soushin.sunshine.R
import me.soushin.sunshine.data.api.dto.Forecast
import me.soushin.sunshine.ui.util.RecycleBinder
import me.soushin.sunshine.ui.util.ViewType

class ForecastChipGroupBinder<V : ViewType>(context: Context,
                                            viewType: V,
                                            private val forecasts: List<Forecast>) : RecycleBinder<V>(context, viewType) {

    override fun layoutResId() = R.layout.forecast_chip_group_binder

    override fun onCreateViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ViewHolder

        forecasts.forEach {
            viewHolder.chipGroup.addView(ForecastChipView(context).apply {
                bindForecast(it)
            })
        }
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chipGroup: ChipGroup by bindView(R.id.chipGroup)
    }
}
