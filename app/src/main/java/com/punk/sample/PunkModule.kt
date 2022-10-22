package com.punk.sample

import android.content.Context
import com.google.gson.Gson
import com.punk.sample.businesslayer.BeerProvider
import com.punk.sample.datalayer.IPunkApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PunkModule(
	private val context: Context
) {

	fun createModule() = module {
		single<IPunkApi> {
			val baseUrl = context.getString(R.string.punk_base_url)
			val converter = GsonConverterFactory.create(Gson())
			val client = OkHttpClient.Builder()
				.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } )
				.build()

			val retrofit = Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(client)
				.addConverterFactory(converter)
				.build()

			retrofit.create(IPunkApi::class.java)
		}

		factory { BeerProvider() }
	}
}