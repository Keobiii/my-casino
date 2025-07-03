package com.example.casino.domain.usecase

import com.example.casino.data.model.User
import com.example.casino.domain.repository.UserRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateUserUseCase(private val repository: UserRepository) {
    operator fun invoke(userId: String, email: String, onResult: (Boolean) -> Unit) {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        val newUser = User(
            balance = 0,
            cashInHistory = emptyList(),
            cashOutHistory = emptyList(),
            dateCreated = date,
            email = email
        )

        repository.createUserInDatabase(userId, newUser, onResult)
    }
}