package com.example.casino.data.model

import androidx.compose.ui.graphics.Brush

data class Carousel(
    val carouselType: String,
    val title: String,
    val description: String,
    val color: Brush,
    val image: Int
)
