package com.route.islamie_app101.data.apis

import com.route.islamie_app101.data.data_models.radio.RadioResponse
import com.route.islamie_app101.data.data_models.reciters.ReciterResponse
import retrofit2.http.GET

interface WebServices {
    @GET("radios")
    suspend fun loadRadio(): RadioResponse
    @GET("reciters")
    suspend fun loadReciters(): ReciterResponse
}