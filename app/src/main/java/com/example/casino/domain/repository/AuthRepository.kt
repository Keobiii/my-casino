package com.example.casino.domain.repository

import com.example.casino.utils.AuthResponse

interface AuthRepository {
    fun registerWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit)
    fun loginWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit)
}
