package com.route.islamie_app101.domain.repository.radio_repository.radio

import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import com.route.islamie_app101.domain.utils.ApiResult

interface RadioRepository {
    suspend fun getRadiosList(): ApiResult<List<RadioDataModel>>
}