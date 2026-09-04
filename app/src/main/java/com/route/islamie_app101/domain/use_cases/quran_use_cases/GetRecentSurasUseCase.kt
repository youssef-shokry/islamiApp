package com.route.islamie_app101.domain.use_cases.quran_use_cases

import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.domain.repository.quran_repository.recent_suras.RecentSurasRepository
import javax.inject.Inject

class GetRecentSurasUseCase @Inject constructor(private val recentSurasRepository: RecentSurasRepository) {
    operator fun invoke(): List<SuraDataModel> = recentSurasRepository.getRecentSuras()
}