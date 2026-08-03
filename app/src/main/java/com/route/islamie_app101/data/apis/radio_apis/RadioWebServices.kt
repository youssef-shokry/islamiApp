package com.route.islamie_app101.data.apis.radio_apis

import com.route.islamie_app101.data.data_models.radio.RadioResponse
import retrofit2.http.GET

interface RadioWebServices {
    @GET("radios")
    suspend fun loadRadio(): RadioResponse
}