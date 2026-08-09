package com.route.islamie_app101.domain.repository.radio_repository.reciters

import com.route.islamie_app101.data.data_models.reciters.RecitersItem //Todo Fix That
import com.route.islamie_app101.domain.radio.ReciterDataModel
import com.route.islamie_app101.domain.utils.ApiResult

interface RecitersRepository {
    suspend fun getRecitersList(): ApiResult<List<ReciterDataModel>>
}