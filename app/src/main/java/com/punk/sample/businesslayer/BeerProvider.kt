package com.punk.sample.businesslayer

import com.punk.sample.datalayer.IPunkApi
import com.punk.sample.objects.Beer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BeerProvider: KoinComponent {
	protected val api: IPunkApi by inject()

	suspend fun beers(page: Int, limit: Int = DEFAULT_LIMIT): ApiResult<List<Beer>> {
		return try {
			Success(api.beers(page, limit))
		} catch (error: Throwable) {
			Fail(error)
		}
	}

	suspend fun beer(id: Long): ApiResult<Beer> {
		return try {
			Success(api.beer(id))
		} catch (error: Throwable) {
			Fail(error)
		}
	}


	companion object {
		const val DEFAULT_LIMIT = 10
	}
}