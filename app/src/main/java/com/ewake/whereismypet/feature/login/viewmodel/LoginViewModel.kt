package com.ewake.whereismypet.feature.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _uiState: LoginScreenUiState by mutableStateOf(LoginScreenUiState.EnterPhone())

    val uiState: StateFlow<LoginScreenUiState> = snapshotFlow { _uiState }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LoginScreenUiState.EnterPhone()
    )

    fun sendPhoneNumber(phone: String) {
        viewModelScope.launch {
            runCatching {
                _uiState = LoginScreenUiState.Loading
                authRepository.sendLoginPhone(phone)
            }.onSuccess {
                _uiState = LoginScreenUiState.EnterCode(it)
            }.onFailure {
                _uiState = LoginScreenUiState.EnterPhone(it.message)
            }
        }
    }
}

sealed interface LoginScreenUiState {
    data class EnterCode(val credential: String) : LoginScreenUiState
    object Loading : LoginScreenUiState
    data class EnterPhone(val error: String? = null) : LoginScreenUiState
}