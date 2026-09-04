package com.route.islamie_app101.domain.use_cases.quran_use_cases

import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.domain.repository.quran_repository.recent_suras.RecentSurasRepository
import javax.inject.Inject

class AddRecentSuraUseCase @Inject constructor(val recentSurasRepository: RecentSurasRepository) {
    private val maxRecentItems = 5

     operator fun invoke(sura: SuraDataModel) {
        val currentSurasList = recentSurasRepository.getRecentSuras().toMutableList()

        if (currentSurasList.contains(sura)) {
            currentSurasList.remove(sura)
        }
        currentSurasList.add(0, sura)

        if (currentSurasList.size > maxRecentItems) {
            currentSurasList.removeAt(currentSurasList.lastIndex)
        }

        recentSurasRepository.addRecentSura(currentSurasList)
    }
}