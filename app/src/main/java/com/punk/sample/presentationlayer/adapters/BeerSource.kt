package com.punk.sample.presentationlayer.adapters

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.punk.sample.businesslayer.BeerProvider
import com.punk.sample.businesslayer.Fail
import com.punk.sample.businesslayer.Success
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BeerSource : PagingSource<Int, Any>(), KoinComponent {
	val provider by inject<BeerProvider>()

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
		val nextPageNumber = params.key ?: 1
		val apiResult = provider.beers(nextPageNumber)

		return when(apiResult) {
			is Success -> {
				LoadResult.Page(
					data = apiResult.result,
					prevKey = nextPageNumber - 1,
					nextKey = nextPageNumber + 1
				)
			}

			is Fail -> {
				apiResult.error.printStackTrace()
				LoadResult.Error(apiResult.error)
			}
		}
	}

	override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}
