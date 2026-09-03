package com.route.islamie_app101.ui.application_screens.quran_fragments.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel

class RecentDiffUtil : DiffUtil.ItemCallback<SuraDataModel>() {
    override fun areItemsTheSame(
        oldItem: SuraDataModel,
        newItem: SuraDataModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: SuraDataModel,
        newItem: SuraDataModel
    ): Boolean = when {
        oldItem.id != newItem.id -> false

        oldItem.suraNameEn != newItem.suraNameEn -> false

        oldItem.suraNameAr != newItem.suraNameAr -> false

        oldItem.versesNumber != newItem.versesNumber -> false

        else -> true
    }

}