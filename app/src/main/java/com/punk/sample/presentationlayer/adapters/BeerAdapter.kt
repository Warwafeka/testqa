package com.punk.sample.presentationlayer.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.punk.sample.R

class BeerAdapter: PagingDataAdapter<Any, AbstractViewHolder<*>>(Calculator()) {
	override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
		val item = getItem(position)
		holder.bind(item)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
		val iflater = LayoutInflater.from(parent.context)
		val view = iflater.inflate(R.layout.item_beer, parent, false)
		return BeerViewHolder(view)
	}

	@SuppressLint("DiffUtilEquals")
	class Calculator: DiffUtil.ItemCallback<Any>() {
		override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
			return oldItem::class.java == newItem::class.java
		}

		override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
			return oldItem == newItem
		}
	}
}