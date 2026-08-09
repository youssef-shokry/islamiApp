package com.route.islamie_app101.data.repositorys.radio_repository.reciters_tab

import com.route.islamie_app101.data.data_sources.radio.RadioDataSource
import com.route.islamie_app101.data.mappers.ReciterMapper
import com.route.islamie_app101.data.utils.isConnected
import com.route.islamie_app101.domain.radio.ReciterDataModel
import com.route.islamie_app101.domain.repository.radio_repository.reciters.RecitersRepository
import com.route.islamie_app101.domain.utils.ApiResult

class ImpRecitersRepository(
    val recitersDataSource: RadioDataSource,
    val reciterMapper: ReciterMapper
) : RecitersRepository {
    override suspend fun getRecitersList(): ApiResult<List<ReciterDataModel>> {
        return try {
            if (isConnected()) {
                when (val result = recitersDataSource.loadRecitersSources()) {
                    is ApiResult.Success -> {
                        ApiResult.Success(reciterMapper.mapReciterList(result.data))
                    }

                    is ApiResult.Error -> {
                        ApiResult.Error(result.errorMessage)
                    }
                }
            } else ApiResult.Error("Check the Internet connection")
        } catch (t: Throwable) {
            ApiResult.Error(t.localizedMessage ?: "Something went wrong try again")
        }
    }
}