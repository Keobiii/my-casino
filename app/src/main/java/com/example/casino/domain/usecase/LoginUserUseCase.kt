package com.example.casino.domain.usecase

import com.example.casino.domain.repository.AuthRepository
import com.example.casino.utils.AuthResponse

class LoginUserUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String, onResult: (AuthResponse) -> Unit) {
        repository.loginWithEmail(email, password, onResult)
    }
}