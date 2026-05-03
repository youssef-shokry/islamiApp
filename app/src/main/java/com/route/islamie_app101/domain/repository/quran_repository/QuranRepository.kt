package com.route.islamie_app101.domain.repository.quran_repository

import com.route.islamie_app101.domain.data_models.SuraDataModel

interface QuranRepository {
    fun getSurahsList(): List<SuraDataModel>
}