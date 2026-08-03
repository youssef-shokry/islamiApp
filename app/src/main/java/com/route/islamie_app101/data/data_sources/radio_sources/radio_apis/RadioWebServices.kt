package com.route.islamie_app101.data.data_sources.radio_sources.radio_apis

import com.route.islamie_app101.data.data_models.radio.RadioResponse //TODO
import retrofit2.http.GET

interface RadioWebServices {
    @GET("radios")
    suspend fun loadRadio(): RadioResponse
}