package com.route.islamie_app101.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.route.islamie_app101.data.repositorys.ahadeth_repository.ImpAhadethRepository
import com.route.islamie_app101.data.repositorys.quran_repository.ImpQuranRepository
import com.route.islamie_app101.domain.data_models.HadethDataModel
import com.route.islamie_app101.domain.repository.hadeth_repository.AhadethRepository
import com.route.islamie_app101.domain.repository.quran_repository.QuranRepository

class ViewModel: ViewModel(){
    private val quranRepo: QuranRepository = ImpQuranRepository()
    private val ahadethRepo: AhadethRepository = ImpAhadethRepository()
    val suraList = quranRepo.getSurahsList()
    private var ahadethList: List<HadethDataModel> = emptyList()
    var selectSuraRvLastPosition = 0
    var selectSuraRvLastPositionOffset = 0

    fun getAhadethList(context: Context): List<HadethDataModel>{
        ahadethList = ahadethRepo.getAhadethList(context)
        return ahadethList
    }

}