package com.ewake.whereismypet.feature.ads.addetail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewake.whereismypet.core.model.AdModel
import com.ewake.whereismypet.core.model.Result
import com.ewake.whereismypet.core.model.asResult
import com.ewake.whereismypet.data.repository.AdsRepository
import com.ewake.whereismypet.feature.ads.addetail.navigation.AdDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class AdDetailViewModel @Inject constructor(
    private val adsRepository: AdsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val adId: String = checkNotNull(
        savedStateHandle[AdDetailDestination.adIdArg]
    )

    private val adStream: Flow<Result<AdModel?>> = adsRepository.getAd(adId).asResult()

    val uiState: StateFlow<AdDetailScreenUiState> = adStream.map { result ->
        return@map when (result) {
            is Result.Error -> AdDetailScreenUiState(AdModelUiState.Error)
            is Result.Loading -> AdDetailScreenUiState(AdModelUiState.Loading)
            is Result.Success -> {
                if (result.data != null) {
                    AdDetailScreenUiState(AdModelUiState.Success(result.data))
                } else {
                    AdDetailScreenUiState(AdModelUiState.Error)
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AdDetailScreenUiState(AdModelUiState.Loading)
    )
}

sealed interface AdModelUiState {
    data class Success(val data: AdModel) : AdModelUiState
    object Error : AdModelUiState
    object Loading : AdModelUiState
}

data class AdDetailScreenUiState(
    val adModelUiState: AdModelUiState
)