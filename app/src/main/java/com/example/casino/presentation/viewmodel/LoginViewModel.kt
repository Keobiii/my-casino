package com.example.casino.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casino.domain.usecase.LoginUserUseCase
import com.example.casino.utils.AuthResponse
import com.example.casino.utils.ErrorMessageMapper
import com.example.casino.utils.UiState
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    var loginState by mutableStateOf<UiState<Unit>>(UiState.Idle)
        private set

    fun login(email: String, password: String) {
        loginState = UiState.Loading

        loginUserUseCase(email, password) { result ->
            loginState = when (result) {
                is AuthResponse.Success -> UiState.Success(Unit)
                is AuthResponse.Error -> UiState.Error(result.message)
                else -> UiState.Error("Unknown error")
            }
        }
    }

    fun resetLoginState() {
        loginState = UiState.Idle
    }

}
