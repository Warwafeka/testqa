package com.punk.sample

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class SampleInitializer: Initializer<KoinApplication> {
	override fun create(context: Context) = startKoin {
		androidLogger()
		androidContext(context)

		modules(
			PunkModule(context).createModule(),
			ImageModule().createModule()
		)
	}

	override fun dependencies(): MutableList<Class<out Initializer<*>>> {
		return mutableListOf()
	}
}
