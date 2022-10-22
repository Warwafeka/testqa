package com.punk.sample.presentationlayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.punk.sample.presentationlayer.adapters.BeerSource

class BeerViewModel : ViewModel() {
	fun beers() = Pager(PagingConfig(pageSize = 20)) { BeerSource() }
		.flow
		.cachedIn(viewModelScope)
}