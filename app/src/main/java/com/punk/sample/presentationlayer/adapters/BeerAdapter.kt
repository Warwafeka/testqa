package com.punk.sample.presentationlayer.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.punk.sample.R
import com.punk.sample.objects.Beer

class BeerAdapter(val listener: IListener): PagingDataAdapter<Any, AbstractViewHolder<*>>(Calculator()) {
	interface IListener: BeerViewHolder.IListener


	override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
		val item = getItem(position)
		holder.bind(item)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
		val iflater = LayoutInflater.from(parent.context)
		val view = iflater.inflate(R.layout.item_beer, parent, false)
		return BeerViewHolder(view, listener)
	}

	@SuppressLint("DiffUtilEquals")
	class Calculator: DiffUtil.ItemCallback<Any>() {
		override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
			if (oldItem is Beer && newItem is Beer) {
				return oldItem.id == newItem.id
			}

			return false
		}

		override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
			if (oldItem is Beer && newItem is Beer) {
				return oldItem.id == newItem.id
			}

			return false
		}
	}

	fun isEmpty(): Boolean = itemCount == 0
}