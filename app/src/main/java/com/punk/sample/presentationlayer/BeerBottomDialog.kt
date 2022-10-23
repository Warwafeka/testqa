package com.punk.sample.presentationlayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.punk.sample.R
import com.punk.sample.objects.Beer

class BeerBottomDialog: BottomSheetDialogFragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.content_beer, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val beer = arguments?.getSerializable(EXTRA_ITEM) as? Beer
		view.findViewById<TextView>(R.id.description).apply {
			text = beer?.description
		}
	}


	companion object {
		const val TAG = "BeerBottomDialog"

		protected const val EXTRA_ITEM = "EXTRA_ITEM"

		fun newDialog(beer: Beer): BeerBottomDialog {
			val bundle = Bundle().apply {
				putSerializable(EXTRA_ITEM, beer)
			}

			val dialog = BeerBottomDialog().apply {
				arguments = bundle
			}

			return dialog
		}
	}
}