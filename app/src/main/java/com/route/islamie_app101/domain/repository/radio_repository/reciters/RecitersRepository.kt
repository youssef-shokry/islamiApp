package com.route.islamie_app101.domain.repository.radio_repository.reciters

import com.route.islamie_app101.domain.data_models.radio.ReciterDataModel
import com.route.islamie_app101.domain.utils.ApiResult

interface RecitersRepository {
    suspend fun getRecitersList(): ApiResult<List<ReciterDataModel>>
}