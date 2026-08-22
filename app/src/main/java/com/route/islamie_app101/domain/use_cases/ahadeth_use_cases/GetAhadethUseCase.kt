package com.route.islamie_app101.domain.use_cases.ahadeth_use_cases

import com.route.islamie_app101.domain.data_models.hadeth.HadethDataModel
import com.route.islamie_app101.domain.repository.hadeth_repository.AhadethRepository
import javax.inject.Inject

class GetAhadethUseCase @Inject constructor(val ahadethRepo: AhadethRepository) {
    operator fun invoke(): List<HadethDataModel> = ahadethRepo.getAhadethList()
}