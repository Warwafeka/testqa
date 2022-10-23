package com.punk.sample.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Beer: Serializable {
	@SerializedName("id") var id = -1L
	@SerializedName("name") var name = ""
	@SerializedName("image_url") var image = ""
	@SerializedName("description") var description = ""
}