package com.example.casino.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.casino.domain.usecase.CreateUserUseCase
import com.example.casino.domain.usecase.RegisterUserUseCase
import com.example.casino.utils.AuthResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import javax.inject.Inject

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    var registerState by mutableStateOf<AuthResponse?>(null)
        private set

    fun register(email: String, password: String) {
        registerUserUseCase(email, password) { result ->
            registerState = result

            if (result is AuthResponse.Success) {
                val uid = Firebase.auth.currentUser?.uid ?: return@registerUserUseCase
                createUserUseCase(uid, email) { /* optional handle success */ }
            }
        }
    }

    fun resetRegisterState() {
        registerState = null
    }
}

