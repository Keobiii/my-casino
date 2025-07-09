package com.example.casino.domain.usecase

import com.example.casino.data.model.PaymentLinkResult
import com.example.casino.domain.repository.PaymentRepository

class CreatePaymentLinkUseCase(
    private val repository: PaymentRepository
) {
    suspend operator fun invoke(
        amount: Int,
        description: String,
        onResult: (Result<PaymentLinkResult>) -> Unit
    ) {
        repository.createPaymentLink(amount, description, onResult)
    }
}


