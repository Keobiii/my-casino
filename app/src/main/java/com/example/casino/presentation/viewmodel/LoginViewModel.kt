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
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {
    var loginState by mutableStateOf<AuthResponse?>(null)
        private set

    fun login(email: String, password: String) {
        loginUserUseCase(email, password) { result ->
            loginState = result
        }
    }

    fun resetLoginState() {
        loginState = null
    }
}
