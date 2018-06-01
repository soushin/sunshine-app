package me.soushin.sunshine.ui.util.binder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotterknife.bindView
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.util.RecycleBinder
import me.soushin.sunshine.ui.util.ViewType

class ForecastCityBinder<V : ViewType>(context: Context, viewType: V, private val cityText: String) : RecycleBinder<V>(context, viewType) {

    override fun layoutResId() = R.layout.forecast_city_binder

    override fun onCreateViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ViewHolder
        viewHolder.city.text = cityText
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val city: TextView by bindView(R.id.city)
    }
}
