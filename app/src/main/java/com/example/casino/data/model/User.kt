package com.example.casino.data.model

data class User(
    val email: String = "",
    val balance: Int = 0,
    val cashInHistory: List<String> = emptyList(),
    val cashOutHistory: List<String> = emptyList(),
    val dateCreated: String = ""
)
