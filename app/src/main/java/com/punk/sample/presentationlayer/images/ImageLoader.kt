package com.punk.sample.presentationlayer.images

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoader {
	protected val picasso by lazy { Picasso.get() }

	fun loadImage(image: ImageView, url: String?) {
		if (url.isNullOrBlank()) {
			image.setImageDrawable(null)
			return
		}

		picasso.load(url).into(image)
	}
}