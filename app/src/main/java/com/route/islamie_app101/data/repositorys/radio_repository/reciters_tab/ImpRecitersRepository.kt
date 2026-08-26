package com.route.islamie_app101.data.repositorys.radio_repository.reciters_tab

import com.route.islamie_app101.data.data_sources.radio.RadioDataSource
import com.route.islamie_app101.data.mappers.ReciterMapper
import com.route.islamie_app101.data.utils.NetworkConnectivity
import com.route.islamie_app101.domain.data_models.radio.ReciterDataModel
import com.route.islamie_app101.domain.repository.radio_repository.reciters.RecitersRepository
import com.route.islamie_app101.domain.utils.ApiResult
import javax.inject.Inject

class ImpRecitersRepository @Inject constructor(
    private val recitersDataSource: RadioDataSource,
    private val reciterMapper: ReciterMapper,
    private val connectivity: NetworkConnectivity
) : RecitersRepository {
    override suspend fun getRecitersList(): ApiResult<List<ReciterDataModel>> {
        return try {
            if (connectivity.isConnected()) {
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