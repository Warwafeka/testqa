package com.punk.sample.presentationlayer.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.punk.sample.R
import com.punk.sample.objects.Beer
import com.punk.sample.presentationlayer.images.ImageLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BeerViewHolder(view: View, val listener: IListener): AbstractViewHolder<Beer>(view), KoinComponent {
	interface IListener {
		fun onBeerClicked(beer: Beer)
	}


	protected val imageLoader by inject<ImageLoader>()
	protected val image by lazy { view.findViewById<ImageView>(R.id.image) }
	protected val name by lazy { view.findViewById<TextView>(R.id.name) }

	init {
		itemView.setOnClickListener { view ->
			item?.let { listener.onBeerClicked(it) }
		}
	}

	override fun bindView() {
		imageLoader.loadImage(image, item?.image)

		name.text = item?.name
	}
}