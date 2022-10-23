package com.punk.sample

import com.punk.sample.presentationlayer.images.ImageLoader
import org.koin.dsl.module

class ImageModule {
	fun createModule() = module {
		single { ImageLoader() }
	}
}