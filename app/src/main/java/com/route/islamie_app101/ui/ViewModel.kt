package com.route.islamie_app101.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.route.islamie_app101.data.repositorys.ahadeth_repository.ImpAhadethRepository
import com.route.islamie_app101.data.repositorys.quran_repository.ImpQuranRepository
import com.route.islamie_app101.domain.data_models.HadethDataModel
import com.route.islamie_app101.domain.use_cases.ahadeth_use_cases.GetAhadethUseCase
import com.route.islamie_app101.domain.use_cases.quran_use_cases.GetQuranUseCase

class ViewModel : ViewModel() {
    private val quranUseCase: GetQuranUseCase = GetQuranUseCase(ImpQuranRepository())
    private val ahadethUseCase: GetAhadethUseCase = GetAhadethUseCase(ImpAhadethRepository())
    val surasList = quranUseCase.invoke()
    var surasListLastPosition = 0
    var surasListLastPositionOffset = 0

    fun getAhadethList(context: Context): List<HadethDataModel> = ahadethUseCase.invoke(context)
}