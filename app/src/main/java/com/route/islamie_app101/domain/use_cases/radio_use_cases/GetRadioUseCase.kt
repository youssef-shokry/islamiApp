package com.route.islamie_app101.domain.use_cases.radio_use_cases

import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import com.route.islamie_app101.domain.repository.radio_repository.radio.RadioRepository
import com.route.islamie_app101.domain.utils.ApiResult
import javax.inject.Inject

class GetRadioUseCase @Inject constructor(private val repo: RadioRepository) {
    suspend operator fun invoke(): ApiResult<List<RadioDataModel>> = repo.getRadiosList()
}