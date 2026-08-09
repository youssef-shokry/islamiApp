package com.route.islamie_app101.data.mappers

import com.route.islamie_app101.data.data_models.reciters.RecitersItem
import com.route.islamie_app101.domain.radio.ReciterDataModel

class ReciterMapper {
    fun mapReciterItem(recitersItem: RecitersItem?): ReciterDataModel = ReciterDataModel(
        name = recitersItem?.name,
        id = recitersItem?.id,
        moshaf = MoshafMapper().mapMoshafItems(recitersItem?.moshaf)
    )

    fun mapReciterList(recitersItems: List<RecitersItem?>): List<ReciterDataModel> =
        recitersItems.map { recitersItem -> mapReciterItem(recitersItem) }
}