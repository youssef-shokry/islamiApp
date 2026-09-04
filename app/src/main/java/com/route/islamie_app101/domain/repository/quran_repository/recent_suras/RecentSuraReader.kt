package com.route.islamie_app101.domain.repository.quran_repository.recent_suras

import com.route.islamie_app101.domain.data_models.sura.SuraDataModel

interface RecentSuraReader {
    fun getRecentSuras(): List<SuraDataModel>
}