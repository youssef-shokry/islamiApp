package com.route.islamie_app101.data.data_models.reciters

import com.google.gson.annotations.SerializedName

data class MoshafItem(

	@field:SerializedName("server")
	val server: String? = null,

	@field:SerializedName("moshaf_type")
	val moshafType: Int? = null,

	@field:SerializedName("rewaya_id")
	val rewayaId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("surah_list")
	val surahList: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("surah_total")
	val surahTotal: Int? = null
)