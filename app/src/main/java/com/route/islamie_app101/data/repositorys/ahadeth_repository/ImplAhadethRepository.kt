package com.route.islamie_app101.data.repositorys.ahadeth_repository

import com.route.islamie_app101.data.data_sources.hadeth.HadethDataSource
import com.route.islamie_app101.domain.data_models.hadeth.HadethDataModel
import com.route.islamie_app101.domain.repository.hadeth_repository.AhadethRepository
import javax.inject.Inject

class ImplAhadethRepository @Inject constructor(private val hadethDataSource: HadethDataSource) :
    AhadethRepository {
    override fun getAhadethList(): List<HadethDataModel> =
        hadethDataSource.ahadethList()
}