package com.route.islamie_app101.domain.use_cases.radio_use_cases

import com.route.islamie_app101.domain.data_models.radio.ReciterDataModel
import com.route.islamie_app101.domain.repository.radio_repository.reciters.RecitersRepository
import com.route.islamie_app101.domain.utils.ApiResult
import javax.inject.Inject

class GetReciterUseCase @Inject constructor(private val repo: RecitersRepository) {
    suspend operator fun invoke(): ApiResult<List<ReciterDataModel>> = repo.getRecitersList()
}