package com.route.islamie_app101.data.repositorys.radio_repository.radio_tab

import com.route.islamie_app101.data.data_models.radio.RadiosItem
import com.route.islamie_app101.domain.repository.radio_repository.radio.RadioRepository

class ImplRadioRepository : RadioRepository {
    override suspend fun getRadioList(): List<RadiosItem> {
        TODO("Not yet implemented")
    }
}