package com.route.islamie_app101.ui.application_screens.radio_fragments.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.route.islamie_app101.domain.data_models.radio.diff_util.DiffIdentifiable

class DiffItemCallback<T : DiffIdentifiable> :
    DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.uniqueId == newItem.uniqueId

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}