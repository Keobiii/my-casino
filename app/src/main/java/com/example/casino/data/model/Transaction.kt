package com.example.casino.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Transaction(
    val type: String,
    val date: String,
    val amount: Float,
    val icon: ImageVector
)