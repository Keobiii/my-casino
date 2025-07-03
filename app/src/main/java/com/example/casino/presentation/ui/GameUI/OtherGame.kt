package com.example.casino.presentation.ui.GameUI

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.casino.ui.theme.pageBackground

@Composable
fun OtherGame(index: Int, fontFamily: FontFamily, title: String) {
    val constraints = ConstraintSet {
        val headerContent = createRefFor("headerContent")
        val headMainContent = createRefFor("headMainContent")
        val mainContent = createRefFor("mainContent")
        val belowMain = createRefFor("belowMain")
        val footerContent = createRefFor("footerContent")

        constrain(mainContent) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }



    }

    ConstraintLayout(
        constraints,
        modifier = Modifier.fillMaxSize().background(pageBackground).padding(16.dp).border(1.dp, Color.Red)
    ) {
        Box(
            modifier = Modifier.size(300.dp).layoutId("mainContent").border(1.dp, Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                fontSize = 24.sp
            )
        }
    }
}