package com.punk.sample.presentationlayer.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.punk.sample.R
import com.punk.sample.objects.Beer

class BeerViewHolder(view: View): AbstractViewHolder<Beer>(view) {
	protected val image by lazy { view.findViewById<ImageView>(R.id.image) }
	protected val name by lazy { view.findViewById<TextView>(R.id.name) }


	override fun bindView() {
		name.text = item?.name
	}
}