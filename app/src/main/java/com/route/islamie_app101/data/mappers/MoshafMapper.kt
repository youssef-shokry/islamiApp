package com.route.islamie_app101.data.mappers

import com.route.islamie_app101.data.data_models.reciters.MoshafItem
import com.route.islamie_app101.domain.data_models.radio.MoshafDataModel
import javax.inject.Inject

class MoshafMapper @Inject constructor(){
    fun mapMoshafItem(moshafItem: MoshafItem?): MoshafDataModel = MoshafDataModel(
        server = moshafItem?.server,
        surahList = moshafItem?.surahList,
        rewayaId = moshafItem?.rewayaId
    )

    fun mapMoshafItems(moshafItems: List<MoshafItem?>?): List<MoshafDataModel> =
        moshafItems?.map { moshafItem -> mapMoshafItem(moshafItem) } ?: emptyList()
}