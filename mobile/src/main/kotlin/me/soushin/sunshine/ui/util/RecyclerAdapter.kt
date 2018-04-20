package me.soushin.sunshine.ui.util

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecyclerAdapter : BaseRecyclerAdapter<RecyclerView.ViewHolder>() {

    fun <B : Binder<RecyclerView.ViewHolder>> replaceAll(objects: List<B>) {
        clear()
        objects.withIndex().forEach {
            insert(it.index, it.value)
        }
        notifyDataSetChanged()
    }
}

open class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val mObjects: MutableList<Binder<VH>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): VH = getItemByViewType(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: VH, position: Int) = getItem(position).onBindViewHolder(holder, position)

    override fun getItemCount(): Int = mObjects.size


    fun getItem(position: Int): Binder<VH> =
            mObjects.withIndex().filter { it.index == position }
                    .takeIf { it.isNotEmpty() }?.let { it[0].value }
                    ?: throw IllegalArgumentException("invalid position=${position}")

    private fun getItemByViewType(viewType: Int): Binder<VH> =
            mObjects.filter { it.getViewType() == viewType }
                    .takeIf { it.isNotEmpty() }?.let { it[0] }
                    ?: throw IllegalArgumentException("invalid viewType=${viewType}")

    fun insert(index: Int, obj: Binder<VH>) {
        mObjects.add(index, obj)
    }

    fun clear() {
        mObjects.clear()
    }
}

interface Binder<VH> {
    fun onCreateViewHolder(parent: ViewGroup): VH
    fun onBindViewHolder(viewHolder: VH, position: Int)
    fun getViewType(): Int
}

interface ViewType {
    fun viewType(): Int
}

abstract class RecycleBinder(private val context: Context, private val viewType: ViewType) : Binder<RecyclerView.ViewHolder> {

    @LayoutRes
    abstract fun layoutResId(): Int

    abstract fun onCreateViewHolder(view: View): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return onCreateViewHolder(LayoutInflater.from(context).inflate(layoutResId(), parent, false))
    }

    override fun getViewType(): Int {
        return viewType.viewType()
    }
}
