package com.example.casino.utils

import androidx.compose.runtime.Composable

data class Shop_TabItem(
    val title: String,
    val page: @Composable () -> Unit
)