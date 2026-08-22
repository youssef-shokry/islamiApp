package com.route.islamie_app101.domain.repository.hadeth_repository

import com.route.islamie_app101.domain.data_models.hadeth.HadethDataModel

interface AhadethRepository {
    fun getAhadethList() : List<HadethDataModel>
}