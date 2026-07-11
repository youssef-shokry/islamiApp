package com.route.islamie_app101.domain.use_cases.quran_use_cases

import com.route.islamie_app101.domain.data_models.SuraDataModel
import com.route.islamie_app101.domain.repository.quran_repository.QuranRepository

class GetQuranUseCase(val quranRepo: QuranRepository) {

    fun invoke(): List<SuraDataModel> {
        return quranRepo.getSurahsList()
    }

}