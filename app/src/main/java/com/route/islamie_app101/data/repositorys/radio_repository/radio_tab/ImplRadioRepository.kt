package com.route.islamie_app101.data.repositorys.radio_repository.radio_tab

import com.route.islamie_app101.data.data_sources.radio.RadioDataSource
import com.route.islamie_app101.data.mappers.RadioMapper
import com.route.islamie_app101.data.utils.NetworkConnectivity
import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import com.route.islamie_app101.domain.repository.radio_repository.radio.RadioRepository
import com.route.islamie_app101.domain.utils.ApiResult
import javax.inject.Inject

class ImplRadioRepository @Inject constructor(
    private val radioDataSource: RadioDataSource,
    private val radioMapper: RadioMapper,
    private val connectivity: NetworkConnectivity
) : RadioRepository {
    override suspend fun getRadiosList(): ApiResult<List<RadioDataModel>> {
        return try {
            if (connectivity.isConnected()) {
                when (val result = radioDataSource.loadRadioSources()) {
                    is ApiResult.Success -> {
                        ApiResult.Success(radioMapper.mapRadioList(result.data))
                    }

                    is ApiResult.Error -> {
                        ApiResult.Error(result.errorMessage)
                    }
                }
            } else {
                ApiResult.Error("Check the Internet Connection")
            }
        } catch (t: Throwable) {
            ApiResult.Error(t.localizedMessage ?: "Something went wrong try again")
        }
    }
}