package com.example.casino.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.casino.domain.usecase.CreateUserUseCase
import com.example.casino.domain.usecase.RegisterUserUseCase
import com.example.casino.utils.AuthResponse
import com.example.casino.utils.UiState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import javax.inject.Inject

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    var registerState by mutableStateOf<UiState<Unit>>(UiState.Idle)
        private set

    fun register(email: String, password: String) {
        registerState = UiState.Loading

        registerUserUseCase(email, password) { result ->
            registerState = when (result) {
                is AuthResponse.Success -> UiState.Success(Unit)
                is AuthResponse.Error -> UiState.Error(result.message)
                else -> UiState.Error("Unknown Error")
            }
        }
    }

    fun resetRegisterState() {
        registerState = UiState.Idle
    }
}

