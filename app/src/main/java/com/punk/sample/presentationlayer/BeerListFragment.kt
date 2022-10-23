package com.punk.sample.presentationlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.punk.sample.R
import com.punk.sample.objects.Beer
import com.punk.sample.presentationlayer.adapters.BeerAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BeerListFragment : Fragment(), BeerAdapter.IListener {
	interface IListener {
		fun showBeer(beer: Beer)
	}

	protected val viewModel by viewModels<BeerViewModel>()

	protected val beerAdapter = BeerAdapter(this)


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.content_beer_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		view.findViewById<RecyclerView>(R.id.recycler).apply {
			layoutManager = GridLayoutManager(context, 2)
			adapter = beerAdapter
		}

		lifecycleScope.launch {
			viewModel.beers().collectLatest { beerAdapter.submitData(it) }
		}
	}


	companion object {
		fun newInstance() = BeerListFragment()
	}

	override fun onBeerClicked(beer: Beer) {
		(activity as? IListener)?.showBeer(beer)
	}
}
