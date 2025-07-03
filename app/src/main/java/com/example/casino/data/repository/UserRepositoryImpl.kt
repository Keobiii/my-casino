package com.example.casino.data.repository

import com.example.casino.data.model.User
import com.example.casino.domain.repository.UserRepository
import com.example.casino.utils.AuthResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl : UserRepository {
    private val auth = Firebase.auth
    private val database = FirebaseDatabase.getInstance().reference

    override fun createUserInDatabase(userId: String, user: User, onResult: (Boolean) -> Unit) {
        database.child("users").child(userId).setValue(user)
            .addOnCompleteListener{ task ->
                onResult(task.isSuccessful)
            }
    }

    override fun loginWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    onResult(AuthResponse.Success)
                } else {
                    onResult(AuthResponse.Error(task.exception?.message ?: "Unknown Error"))
                }
            }
    }
}