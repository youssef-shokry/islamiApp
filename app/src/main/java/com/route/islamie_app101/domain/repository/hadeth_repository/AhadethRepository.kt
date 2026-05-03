package com.route.islamie_app101.domain.repository.hadeth_repository

import android.content.Context
import com.route.islamie_app101.domain.data_models.HadethDataModel

interface AhadethRepository {
    fun getAhadethList(context: Context) : List<HadethDataModel>
}