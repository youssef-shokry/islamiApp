package com.route.islamie_app101.data.apis

import com.route.islamie_app101.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWebServices() : WebServices = retrofit.create(WebServices::class.java)
}