package me.soushin.sunshine.ui.util.binder

import android.content.Context
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import me.soushin.sunshine.R
import me.soushin.sunshine.ui.util.RecycleBinder
import me.soushin.sunshine.ui.util.ViewType


class IntentDividerBinder<V : ViewType>(context: Context,
                                        viewType: V, @DimenRes private val margin: Int? = null) : RecycleBinder<V>(context, viewType) {

    override fun layoutResId() = R.layout.intent_divider

    override fun onCreateViewHolder(view: View) = ViewHolder(view)

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder as ViewHolder
        margin?.let(context.resources::getDimensionPixelSize)?.let {
            (viewHolder.dividerLayout.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
                leftMargin = it
            }
        }
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dividerLayout: FrameLayout = view.findViewById(R.id.dividerLayout)
    }
}
