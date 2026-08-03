package com.route.islamie_app101.data.data_models.reciters

import com.google.gson.annotations.SerializedName

data class RecitersItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("letter")
	val letter: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("moshaf")
	val moshaf: List<MoshafItem?>? = null
)