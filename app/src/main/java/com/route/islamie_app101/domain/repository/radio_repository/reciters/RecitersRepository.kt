package com.route.islamie_app101.domain.repository.radio_repository.reciters

import com.route.islamie_app101.data.data_models.reciters.RecitersItem //Todo Fix That

interface RecitersRepository {
    suspend fun getRecitersList(): List<RecitersItem>
}