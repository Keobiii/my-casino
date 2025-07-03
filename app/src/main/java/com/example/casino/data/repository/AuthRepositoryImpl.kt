package com.example.casino.data.repository

import com.example.casino.domain.repository.AuthRepository
import com.example.casino.utils.AuthResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth

    override fun registerWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(AuthResponse.Success)
                } else {
                    onResult(AuthResponse.Error(task.exception?.message ?: "Registration failed"))
                }
            }
    }

    override fun loginWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(AuthResponse.Success)
                } else {
                    onResult(AuthResponse.Error(task.exception?.message ?: "Login failed"))
                }
            }
    }
}
