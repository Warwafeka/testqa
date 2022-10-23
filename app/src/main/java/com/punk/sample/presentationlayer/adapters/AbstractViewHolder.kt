package com.punk.sample.presentationlayer.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<T>(view: View): RecyclerView.ViewHolder(view) {
	protected var item: T? = null

	fun bind(item: Any?) {
		this.item = item as? T

		bindView()
	}

	protected abstract fun bindView()
}