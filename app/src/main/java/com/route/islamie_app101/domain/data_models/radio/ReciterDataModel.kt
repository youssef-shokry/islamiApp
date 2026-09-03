package com.route.islamie_app101.domain.data_models.radio

import com.route.islamie_app101.domain.data_models.radio.diff_util.DiffIdentifiable

data class ReciterDataModel(
    val name: String? = null,
    val id: Int? = null,
    val moshaf: List<MoshafDataModel?>? = null
): DiffIdentifiable{
    override val uniqueId: Int?
        get() = id
}