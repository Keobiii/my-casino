package com.example.casino.domain.repository

import androidx.lifecycle.LiveData
import com.example.casino.data.model.User
import com.example.casino.utils.AuthResponse

interface UserRepository {
    fun createUserInDatabase(userId: String, user: User, onResult: (Boolean) -> Unit)
    fun loginWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit)
    fun observeUserData(userId: String): LiveData<User>
    fun updateUserField(
        uid: String,
        updates: Map<String, Any>,
        onResult: (Boolean) -> Unit
    )
}