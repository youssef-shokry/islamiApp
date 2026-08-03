package com.route.islamie_app101.data.data_models.reciters

import com.google.gson.annotations.SerializedName

data class ReciterResponse(
	@field:SerializedName("reciters")
	val reciters: List<RecitersItem?>? = null
)