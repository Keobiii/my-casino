package com.example.casino.data.model

data class Transaction(
    val type: String,
    val date: String,
    val amount: Float,
    val icon: Int
)