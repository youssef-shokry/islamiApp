package com.route.islamie_app101.data.data_sources.radio


import com.route.islamie_app101.data.apis.WebServices
import com.route.islamie_app101.data.data_models.radio.RadiosItem
import com.route.islamie_app101.data.data_models.reciters.RecitersItem
import com.route.islamie_app101.domain.utils.ApiResult

class RadioDataSource(
    private val webServices: WebServices
) {
    suspend fun loadRadioSources(): ApiResult<List<RadiosItem?>> {
        return try {
            val response = webServices.loadRadio()
            ApiResult.Success(response.radios ?: emptyList())
        } catch (t: Throwable) {
            ApiResult.Error(t.localizedMessage ?: "Radio Data Source Error")
        }
    }

    suspend fun loadRecitersSources(): ApiResult<List<RecitersItem?>> {
        return try {
            val response = webServices.loadReciters()
            ApiResult.Success(response.reciters ?: emptyList())
        } catch (t: Throwable) {
           ApiResult.Error(t.localizedMessage ?: "Radio Data Source Error")
        }
    }
}