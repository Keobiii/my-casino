package com.example.casino.domain.repository

import com.example.casino.data.model.PaymentLinkResult

interface PaymentRepository {
    suspend fun createPaymentLink(
        amount: Int,
        description: String,
        onResult: (Result<PaymentLinkResult>) -> Unit
    )

}