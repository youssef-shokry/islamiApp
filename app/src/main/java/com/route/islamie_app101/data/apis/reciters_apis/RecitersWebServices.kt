package com.route.islamie_app101.data.apis.reciters_apis

import com.route.islamie_app101.data.data_models.reciters.ReciterResponse
import retrofit2.http.GET

interface RecitersWebServices {
    @GET("reciters")
    suspend fun loadReciters(): ReciterResponse
}