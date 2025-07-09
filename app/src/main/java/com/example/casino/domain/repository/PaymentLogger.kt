package com.example.casino.domain.repository

import com.example.casino.data.model.PayMongoPayment

interface PaymentLogger {
    fun logPayment(userId: String, payment: PayMongoPayment, onComplete: (Boolean) -> Unit)
    fun updatePaymentStatus(userId: String, referenceNumber: String, status: String)

}
