package me.soushin.sunshine.ui.forecasts.binder

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.util.RecycleBinder
import me.soushin.sunshine.ui.util.ViewType

class ForecastIconAuthorBinder<V : ViewType>(context: Context, viewType: V) : RecycleBinder<V>(context, viewType) {

    override fun layoutResId() = R.layout.forecast_icon_author_binder

    override fun onCreateViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ViewHolder
        viewHolder.iconAuthorLink.setLinkableHtml(context.getString(R.string.weather_icon_made_by), {
            Intent(Intent.ACTION_VIEW, Uri.parse(it)).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }.run {
                context.startActivity(this)
            }
        })
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconAuthorLink: TextView = view.findViewById(R.id.iconAuthorLink)
    }
}

fun TextView.setLinkableHtml(html: String, onClickLink: (String) -> Unit) {
    val sequence = Html.fromHtml(html)
    val strBuilder = SpannableStringBuilder(sequence)
    val urls = strBuilder.getSpans(0, sequence.length, URLSpan::class.java)
    urls.forEach {
        val clickable = object : ClickableSpan() {
            override fun onClick(widget: View?) {
                onClickLink.invoke(it.url)
            }
        }
        strBuilder.setSpan(clickable, strBuilder.getSpanStart(it), strBuilder.getSpanEnd(it), strBuilder.getSpanFlags(it))
        strBuilder.removeSpan(it)
    }
    text = strBuilder
    movementMethod = LinkMovementMethod.getInstance()
}
