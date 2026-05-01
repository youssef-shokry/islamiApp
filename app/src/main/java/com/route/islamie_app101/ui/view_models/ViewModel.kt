package com.route.islamie_app101.ui.view_models

import androidx.lifecycle.ViewModel
import com.route.islamie_app101.data.repositorys.quran_repository.ImpQuranRepository
import com.route.islamie_app101.domain.repository.quran_repository.QuranReository


class ViewModel: ViewModel(){
    private val quranRepo: QuranReository = ImpQuranRepository()
    val suraList = quranRepo.getSurahsList()
}