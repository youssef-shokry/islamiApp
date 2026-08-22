package com.route.islamie_app101.domain.use_cases.quran_use_cases

import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.domain.repository.quran_repository.QuranRepository
import javax.inject.Inject

class GetQuranUseCase @Inject constructor(val quranRepo: QuranRepository) {
    operator fun invoke(): List<SuraDataModel> = quranRepo.getSurahsList()
}