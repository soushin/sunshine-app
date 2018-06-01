package me.soushin.sunshine.ui.home.binder

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_NO_YEAR
import android.view.View
import android.widget.TextView
import kotterknife.bindView
import me.soushin.sunshine.R
import me.soushin.sunshine.data.api.dto.CurrentWeather
import me.soushin.sunshine.ui.util.RecycleBinder
import me.soushin.sunshine.ui.util.ViewType

class CurrentWeatherViewBinder<V : ViewType>(context: Context,
                                             viewType: V,
                                             private val currentWeather: CurrentWeather) : RecycleBinder<V>(context, viewType) {

    override fun layoutResId() = R.layout.current_weather_binder

    override fun onCreateViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ViewHolder

        viewHolder.date.text = DateUtils.formatDateTime(context, currentWeather.dt * 1000L, FORMAT_NO_YEAR)
        viewHolder.weather.text = currentWeather.weather.get(0).main
        currentWeather.main.let {
            viewHolder.temperature.text = context.getString(R.string.forecast_temperature, it.tempMax.toString(), it.tempMin.toString())
        }
        currentWeather.weather.get(0).main.let {
            viewHolder.weather.text = it
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

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView by bindView(R.id.forecast_date)
        val weather: TextView by bindView(R.id.forecast_weather)
        val temperature: TextView by bindView(R.id.forecast_temperature)
        val image: AppCompatImageView by bindView(R.id.forecast_ic)
    }
}
