package com.route.islamie_app101.domain.repository.radio_repository.radio

import com.route.islamie_app101.data.data_models.radio.RadiosItem // TODO Fix This

interface RadioRepository {
    suspend fun getRadioList(): List<RadiosItem>
}