package com.route.islamie_app101.ui.application_screens.hadeth_fragments.hadeth_view_pager

import com.route.islamie_app101.domain.data_models.hadeth.HadethDataModel

interface SetOnHadethClick {
    fun onHadethClick(hadeth: HadethDataModel)
}