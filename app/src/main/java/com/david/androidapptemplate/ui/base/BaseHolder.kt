package com.david.androidapptemplate.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseHolder<T>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener {

    abstract fun onBind(dataItem: T)

    override fun onClick(v: View?) {}

    open fun onViewAttachedToWindow() {}

    open fun onViewDetachedFromWindow() {}
}
