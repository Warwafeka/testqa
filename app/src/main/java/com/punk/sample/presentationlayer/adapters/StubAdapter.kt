package com.punk.sample.presentationlayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.punk.sample.R

class StubAdapter(val listener: IListener): LoadStateAdapter<AbstractViewHolder<*>>() {
	interface IListener: StubViewHolder.IListener


	override fun getStateViewType(loadState: LoadState): Int {
		return VIEW_TYPE_STUB
	}

	override fun onBindViewHolder(holder: AbstractViewHolder<*>, loadState: LoadState) {
		holder.bind(loadState)
	}

	override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): AbstractViewHolder<*> {
		val iflater = LayoutInflater.from(parent.context)
		val view = iflater.inflate(R.layout.item_stub, parent, false)
		return StubViewHolder(view, listener)
	}

	companion object {
		const val VIEW_TYPE_STUB = -1
	}
}