package com.punk.sample.datalayer

import com.punk.sample.objects.Beer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IPunkApi {
	@GET("v2/beers")
	suspend fun beers(@Query("page") page: Int, @Query("per_page") limit: Int): List<Beer>

	@GET("v2/beer/{id}}")
	suspend fun beer(@Path("id") id: Long): Beer
}