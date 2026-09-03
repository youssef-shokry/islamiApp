package com.route.islamie_app101.ui

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import com.route.islamie_app101.domain.data_models.hadeth.HadethDataModel
import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import com.route.islamie_app101.domain.data_models.radio.ReciterDataModel
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.domain.use_cases.ahadeth_use_cases.GetAhadethUseCase
import com.route.islamie_app101.domain.use_cases.quran_use_cases.GetQuranUseCase
import com.route.islamie_app101.domain.use_cases.radio_use_cases.GetRadioUseCase
import com.route.islamie_app101.domain.use_cases.radio_use_cases.GetReciterUseCase
import com.route.islamie_app101.domain.utils.ApiResult
import com.route.islamie_app101.ui.utils.Resource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@HiltViewModel
class IslamiViewModel @Inject constructor(
    quranUseCase: GetQuranUseCase,
    ahadethUseCase: GetAhadethUseCase,
    private val radioUseCase: GetRadioUseCase,
    private val reciterUseCase: GetReciterUseCase
) : ViewModel() {
    val surasList: List<SuraDataModel> = quranUseCase()
    var recentSurasList: MutableList<SuraDataModel> = mutableListOf()
    val ahadethList: List<HadethDataModel> = ahadethUseCase()
    var radioState = MutableLiveData<Resource<List<RadioDataModel>>>()
    var recitersState = MutableLiveData<Resource<List<ReciterDataModel>>>()

    fun loadRadioList() {
        radioState.value = Resource.Loading()
        viewModelScope.launch {
            try {
                when (val response = radioUseCase.invoke()) {
                    is ApiResult.Success -> radioState.value = Resource.Success(response.data)
                    is ApiResult.Error -> radioState.value = Resource.Error(response.errorMessage)
                }
            } catch (t: Throwable) {
                radioState.value =
                    Resource.Error(t.localizedMessage ?: "Something went wrong try again")
            }
        }
    }

    fun loadRecitersList() {
        recitersState.value = Resource.Loading()
        viewModelScope.launch {
            try {
                when (val response = reciterUseCase.invoke()) {
                    is ApiResult.Success -> recitersState.value = Resource.Success(response.data)
                    is ApiResult.Error -> recitersState.value =
                        Resource.Error(response.errorMessage)
                }
            } catch (t: Throwable) {
                recitersState.value =
                    Resource.Error(t.localizedMessage ?: "Something went wrong try again")
            }
        }
    }
}