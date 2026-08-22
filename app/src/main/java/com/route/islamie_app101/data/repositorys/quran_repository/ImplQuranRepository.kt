package com.route.islamie_app101.data.repositorys.quran_repository

import com.route.islamie_app101.data.data_sources.quran.SurahDataSource.surahsList
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.domain.repository.quran_repository.QuranRepository
import javax.inject.Inject

class ImplQuranRepository @Inject constructor() : QuranRepository {
    override fun getSurahsList(): List<SuraDataModel> = surahsList
}