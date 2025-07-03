package com.example.casino.domain.usecase

import androidx.lifecycle.LiveData
import com.example.casino.data.model.User
import com.example.casino.domain.repository.UserRepository

class ObserveUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(uid: String): LiveData<User> {
        return repository.observeUserData(uid)
    }
}
