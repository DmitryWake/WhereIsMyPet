package com.ewake.whereismypet.feature.auth.login.viewmodel

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

    private var credential: String? = null

    fun sendPhoneNumber(phone: String) {
        viewModelScope.launch {
            runCatching {
                _uiState = LoginScreenUiState.Loading
                authRepository.sendLoginPhone(phone)
            }.onSuccess {
                credential = it
                _uiState = LoginScreenUiState.EnterCode()
            }.onFailure {
                _uiState = LoginScreenUiState.EnterPhone(it.message)
            }
        }
    }

    fun sendCode(code: String) {
        if (!credential.isNullOrBlank()) {
            if (code.length != LoginScreenUiState.EnterCode.PHONE_CODE_LENGTH) {
                _uiState = LoginScreenUiState.EnterCode("Несоответствие длины кода")
            }

            viewModelScope.launch {
                runCatching {
                    _uiState = LoginScreenUiState.Loading
                    authRepository.sendPhoneCode(credential!!, code)
                }.onSuccess {
                    _uiState = LoginScreenUiState.Navigating(it.isFirstAuth)
                }.onFailure {
                    _uiState = LoginScreenUiState.EnterCode(it.message)
                }
            }
        } else {
            _uiState = LoginScreenUiState.EnterPhone("Ошибка получения данных, пожалуйста, авторизуйтесь еще раз")
        }
    }
}

sealed interface LoginScreenUiState {
    data class EnterCode(val error: String? = null) : LoginScreenUiState {
        companion object {
            const val PHONE_CODE_LENGTH = 4
        }
    }

    object Loading : LoginScreenUiState
    data class Navigating(val isFirstLogin: Boolean) : LoginScreenUiState
    data class EnterPhone(val error: String? = null) : LoginScreenUiState
}