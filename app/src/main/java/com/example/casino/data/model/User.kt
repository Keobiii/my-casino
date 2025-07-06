package com.example.casino.data.model

data class User(
    val email: String = "",
    val balance: Int = 0,
    val cashInHistory: Map<String, Transaction> = emptyMap(),
    val cashOutHistory: Map<String, Transaction> = emptyMap(),
    val dateCreated: String = ""
)
