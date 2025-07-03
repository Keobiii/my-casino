package com.example.casino.domain.usecase

import com.example.casino.domain.repository.AuthRepository
import com.example.casino.utils.AuthResponse

class RegisterUserUseCase(private val repository: AuthRepository) {
    operator fun invoke(email: String, password: String, onResult: (AuthResponse) -> Unit) {
        repository.registerWithEmail(email, password, onResult)
    }
}
