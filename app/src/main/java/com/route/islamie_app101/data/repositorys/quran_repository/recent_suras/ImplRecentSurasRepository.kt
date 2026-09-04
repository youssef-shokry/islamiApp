package com.route.islamie_app101.data.repositorys.quran_repository.recent_suras

import com.route.islamie_app101.data.data_sources.quran.recent_suras.RecentSurasDataSource
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.domain.repository.quran_repository.recent_suras.RecentSurasRepository
import javax.inject.Inject

class ImplRecentSurasRepository @Inject constructor(private val recentSurasDataSource: RecentSurasDataSource) :
    RecentSurasRepository {

    override fun getRecentSuras(): List<SuraDataModel> =
        recentSurasDataSource.getRecentSuras()

    override fun addRecentSura(suras: List<SuraDataModel>) =
        recentSurasDataSource.saveRecentSuras(suras)
}