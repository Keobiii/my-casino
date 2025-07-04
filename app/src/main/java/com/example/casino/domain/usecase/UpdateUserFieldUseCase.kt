package com.example.casino.domain.usecase

import com.example.casino.domain.repository.UserRepository

class UpdateUserFieldUseCase(private val repository: UserRepository) {
    operator fun invoke(
        uid: String,
        updates: Map<String, Any>,
        onResult: (Boolean) -> Unit
    ) {
        repository.updateUserField(uid, updates, onResult)
    }
}