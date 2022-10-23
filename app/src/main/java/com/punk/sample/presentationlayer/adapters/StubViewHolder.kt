package com.punk.sample.presentationlayer.adapters

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.punk.sample.R
import org.koin.core.component.KoinComponent

class StubViewHolder(view: View, val listener: IListener): AbstractViewHolder<LoadState>(view), KoinComponent {
	interface IListener {
		fun onErrorClicked(error: Throwable?)
	}

	protected val retry by lazy { view.findViewById<ImageView>(R.id.image) }
	protected val progress by lazy { view.findViewById<View>(R.id.progress) }

	init {
		itemView.setOnClickListener { view ->
			val errorItem = item as? LoadState.Error
			errorItem?.let { listener.onErrorClicked(errorItem.error) }
		}
	}

	override fun bindView() {
		retry.isVisible = item is LoadState.Error
		progress.isVisible = item is LoadState.Loading
	}
}