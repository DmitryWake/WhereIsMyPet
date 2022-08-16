package com.ewake.whereismypet.feature.auth.register.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewake.whereismypet.core.model.RegisterModel
import com.ewake.whereismypet.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Nikolaevskiy Dmitriy
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    val uiState: StateFlow<RegisterScreenUiState> = snapshotFlow { _uiState }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RegisterScreenUiState.Default()
    )

    private var _uiState: RegisterScreenUiState by mutableStateOf(RegisterScreenUiState.Default())

    fun onContinueClicked(registerModel: RegisterModel) {
        _uiState = RegisterScreenUiState.Loading

        viewModelScope.launch {
            runCatching {
                register(registerModel)
            }.onSuccess {
                _uiState = RegisterScreenUiState.Navigation
            }.onFailure {
                _uiState = RegisterScreenUiState.Default(error = it.message)
            }
        }
    }

    private suspend fun register(registerModel: RegisterModel) {
        repository.register(registerModel)
    }
}

sealed interface RegisterScreenUiState {
    object Loading : RegisterScreenUiState
    data class Default(val error: String? = null) : RegisterScreenUiState
    object Navigation : RegisterScreenUiState
}