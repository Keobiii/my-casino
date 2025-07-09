package com.example.casino.domain.repository

interface PaymentRepository {
    suspend fun createPaymentLink(
        amount: Int,
        description: String,
        onResult: (Result<String>) -> Unit
    )
}