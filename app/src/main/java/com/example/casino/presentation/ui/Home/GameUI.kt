package com.example.casino.presentation.ui.Home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import com.example.casino.presentation.ui.GameUI.GameOneUI
import com.example.casino.presentation.ui.GameUI.OtherGame


@Composable
fun GameUI(index: Int, fontFamily: FontFamily, title: String) {
    Log.i("Game Index: ", index.toString())
    Log.i("Game Name: ", title)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (index == 1) {
            GameOneUI(
                index = index,
                fontFamily = fontFamily,
                title = title
            )
        } else {
            OtherGame(
                index = index,
                fontFamily = fontFamily,
                title = title
            )
        }

    }

}


