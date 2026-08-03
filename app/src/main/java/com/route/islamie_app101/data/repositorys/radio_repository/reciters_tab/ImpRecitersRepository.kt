package com.route.islamie_app101.data.repositorys.radio_repository.reciters_tab

import com.route.islamie_app101.data.data_models.reciters.RecitersItem
import com.route.islamie_app101.domain.repository.radio_repository.reciters.RecitersRepository

class ImpRecitersRepository : RecitersRepository {
    override suspend fun getRecitersList(): List<RecitersItem> {
        TODO("Not yet implemented")
    }
}