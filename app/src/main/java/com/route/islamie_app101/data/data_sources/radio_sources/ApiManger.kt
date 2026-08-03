package com.route.islamie_app101.data.data_sources.radio_sources

import com.route.islamie_app101.data.data_sources.radio_sources.radio_apis.RadioWebServices
import com.route.islamie_app101.data.data_sources.radio_sources.reciters_apis.RecitersWebServices
import com.route.islamie_app101.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManger {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRadioWebServices(): RadioWebServices = retrofit.create(RadioWebServices::class.java)
    fun getReciterWebServices(): RecitersWebServices =
        retrofit.create(RecitersWebServices::class.java)
}