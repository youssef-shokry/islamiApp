package com.route.islamie_app101.data.mappers

import com.route.islamie_app101.data.data_models.radio.RadiosItem
import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import javax.inject.Inject

class RadioMapper @Inject constructor(){
    fun mapRadioItems(radioItem: RadiosItem?): RadioDataModel = RadioDataModel(
        name = radioItem?.name,
        url = radioItem?.url,
        id = radioItem?.id
    )

    fun mapRadioList(radioItems: List<RadiosItem?>): List<RadioDataModel> =
        radioItems.map { radioItem -> mapRadioItems(radioItem) }
}