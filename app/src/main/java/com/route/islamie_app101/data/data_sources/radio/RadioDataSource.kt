package com.route.islamie_app101.data.data_sources.radio

import com.route.islamie_app101.data.apis.radio_apis.RadioWebServices
import com.route.islamie_app101.data.apis.reciters_apis.RecitersWebServices
import com.route.islamie_app101.data.data_models.radio.RadiosItem
import com.route.islamie_app101.data.data_models.reciters.RecitersItem
import com.route.islamie_app101.data.utils.ApiResult

class RadioDataSource(
    val radioWebServices: RadioWebServices,
    val recitersWebServices: RecitersWebServices
) {
    suspend fun loadRadioSources(): ApiResult<List<RadiosItem?>> {
        return try {
            val response = radioWebServices.loadRadio()
            ApiResult.Success(response.radios ?: emptyList())
        } catch (t: Throwable) {
            ApiResult.Error(t.localizedMessage ?: "Radio Data Source Error")
        }
    }

    suspend fun loadRecitersSources(): ApiResult<List<RecitersItem?>> {
        return try {
            val response = recitersWebServices.loadReciters()
            ApiResult.Success(response.reciters ?: emptyList())
        } catch (t: Throwable) {
           ApiResult.Error(t.localizedMessage ?: "Radio Data Source Error")
        }
    }
}