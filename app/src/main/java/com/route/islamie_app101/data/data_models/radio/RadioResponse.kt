package com.route.islamie_app101.data.data_models.radio

import com.google.gson.annotations.SerializedName

data class RadioResponse(
    @field:SerializedName("radios")
    val radios: List<RadiosItem?>? = null
)