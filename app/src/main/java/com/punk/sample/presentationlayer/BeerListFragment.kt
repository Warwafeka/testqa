package com.punk.sample.presentationlayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.punk.sample.R
import com.punk.sample.objects.Beer
import com.punk.sample.presentationlayer.adapters.BeerAdapter
import com.punk.sample.presentationlayer.adapters.StubAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BeerListFragment : Fragment(), BeerAdapter.IListener, StubAdapter.IListener {
	interface IListener {
		fun showBeer(beer: Beer)
	}

	protected val viewModel by viewModels<BeerViewModel>()

	protected val beerAdapter = BeerAdapter(this)
	protected val concatAdapter by lazy { beerAdapter.withLoadStateFooter(StubAdapter(this)) }


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.content_beer_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		view.findViewById<RecyclerView>(R.id.recycler).apply {
			layoutManager = GridLayoutManager(context, 2)
			adapter = concatAdapter
		}

		lifecycleScope.launch {
			viewModel.beers().collectLatest { beerAdapter.submitData(it) }
		}

		val stub = view.findViewById<View>(R.id.stub)
		val retry = view.findViewById<View>(R.id.retry)
		val progress = view.findViewById<View>(R.id.progress)

		retry.setOnClickListener {
			onErrorClicked(null)
		}

		beerAdapter.addLoadStateListener {
			if (beerAdapter.isEmpty()) {
				stub.isVisible = true
				progress.isVisible = it.refresh is LoadState.Loading
				retry.isVisible = it.refresh is LoadState.Error
			} else {
				stub.isVisible = false
			}
		}
	}


	companion object {
		fun newInstance() = BeerListFragment()
	}

	override fun onBeerClicked(beer: Beer) {
		(activity as? IListener)?.showBeer(beer)
	}

	override fun onErrorClicked(error: Throwable?) {
		beerAdapter.retry()
	}
}
