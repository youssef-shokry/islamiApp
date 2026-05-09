package com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter

import com.route.islamie_app101.domain.data_models.SuraDataModel

interface SelectSuraInterface {
    fun onSuraClick(sura: SuraDataModel)
}