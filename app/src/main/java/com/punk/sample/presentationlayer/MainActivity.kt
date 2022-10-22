package com.punk.sample.presentationlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.punk.sample.R

class MainActivity : AppCompatActivity(), BeerListFragment.IListener {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, BeerListFragment.newInstance())
				.commitNow()
		}
	}

	override fun showBeer(beerId: Long) {

	}
}