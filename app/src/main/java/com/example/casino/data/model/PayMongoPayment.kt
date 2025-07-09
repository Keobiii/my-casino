package com.example.casino.data.model

data class PayMongoPayment(
    val amount: Int = 0,
    val description: String = "",
    val status: String = "unpaid",
    val referenceNumber: String = "",
    val checkoutUrl: String = "",
    val linkId: String = "",
    val createdAt: Long = System.currentTimeMillis()
)