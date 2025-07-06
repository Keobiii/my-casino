package com.example.casino.domain.usecase

import com.example.casino.data.model.Transaction
import com.example.casino.domain.repository.UserRepository

class GetTransactionHistoryUseCase(private val repository: UserRepository) {
    operator fun invoke(uid: String, onResult: (List<Transaction>) -> Unit) {
        repository.getTransactionHistory(uid, onResult)
    }
}

