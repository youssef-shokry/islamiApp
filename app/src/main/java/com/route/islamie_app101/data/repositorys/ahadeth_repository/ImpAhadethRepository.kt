package com.route.islamie_app101.data.repositorys.ahadeth_repository

import android.content.Context
import com.route.islamie_app101.data.data_sources.hadeth.HadethDataSource.ahadethList
import com.route.islamie_app101.domain.data_models.HadethDataModel
import com.route.islamie_app101.domain.repository.hadeth_repository.AhadethRepository

class ImpAhadethRepository : AhadethRepository{

    override fun getAhadethList(context: Context): List<HadethDataModel> {
        return ahadethList(context)
    }

}