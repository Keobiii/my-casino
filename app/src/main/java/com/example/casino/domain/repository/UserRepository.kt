package com.example.casino.domain.repository

import com.example.casino.data.model.User
import com.example.casino.utils.AuthResponse

interface UserRepository {
    fun createUserInDatabase(userId: String, user: User, onResult: (Boolean) -> Unit)
    fun loginWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit)
}