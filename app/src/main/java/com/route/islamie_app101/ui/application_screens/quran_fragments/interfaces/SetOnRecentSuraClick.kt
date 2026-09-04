package com.route.islamie_app101.ui.application_screens.quran_fragments.interfaces

import com.route.islamie_app101.databinding.RecentlySelectedItemBinding
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel

fun interface SetOnRecentSuraClick {
    fun onRecentSuraClick(sura: SuraDataModel)
}