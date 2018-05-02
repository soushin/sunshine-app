package me.soushin.sunshine.ui.forecasts.binder

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_NO_YEAR
import android.view.View
import android.widget.TextView
import me.soushin.sunshine.R
import me.soushin.sunshine.data.api.dto.Forecast
import me.soushin.sunshine.ui.util.RecycleBinder
import me.soushin.sunshine.ui.util.ViewType

class ForecastViewBinder(private val context: Context,
                         private val forecast: Forecast) : RecycleBinder(context, ForecastViewType.FORECAST) {

    override fun layoutResId() = R.layout.forcast_binder

    override fun onCreateViewHolder(view: View): RecyclerView.ViewHolder {
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ViewHolder

        viewHolder.date.text = DateUtils.formatDateTime(context, forecast.dt * 1000L, FORMAT_NO_YEAR)
        viewHolder.main.text = forecast.weather.get(0).main
        viewHolder.max.text = forecast.temp.max.toString()
        viewHolder.min.text = forecast.temp.min.toString()

        forecast.weather.get(0).main.let {
            viewHolder.main.text = it
            when (it.toLowerCase()) {
                "clear" -> R.drawable.ic_sun
                "clouds" -> R.drawable.ic_cloud
                "fog" -> R.drawable.ic_haze
                "light_clouds" -> R.drawable.ic_cloudy
                "light_rain" -> R.drawable.ic_rain
                "rain" -> R.drawable.ic_rain
                "snow" -> R.drawable.ic_snowing
                "storm" -> R.drawable.ic_storm
                else -> R.drawable.ic_sun // fix me
            }.let {
                viewHolder.image.setBackgroundResource(it)
            }
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val date: TextView = view.findViewById(R.id.forecast_date)
    val main: TextView = view.findViewById(R.id.forecast_weather)
    val max: TextView = view.findViewById(R.id.forecast_temp_max)
    val min: TextView = view.findViewById(R.id.forecast_temp_min)
    val image: AppCompatImageView = view.findViewById(R.id.forecast_ic)
}

enum class ForecastViewType : ViewType {
    FORECAST;

    override fun viewType(): Int = ordinal
}
