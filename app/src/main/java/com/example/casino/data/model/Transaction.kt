package com.example.casino.data.model

data class Transaction(
    val transactionId: String = "",
    val date: String = "",
    val type: String = "",
    val amount: Double = 0.00
)