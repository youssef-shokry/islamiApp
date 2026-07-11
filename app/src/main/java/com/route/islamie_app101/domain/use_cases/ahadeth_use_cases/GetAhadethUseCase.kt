package com.route.islamie_app101.domain.use_cases.ahadeth_use_cases

import android.content.Context
import com.route.islamie_app101.domain.data_models.HadethDataModel
import com.route.islamie_app101.domain.repository.hadeth_repository.AhadethRepository

class GetAhadethUseCase(val ahadethRepo: AhadethRepository) {

    fun invoke(context: Context): List<HadethDataModel> {
        return ahadethRepo.getAhadethList(context)
    }

}