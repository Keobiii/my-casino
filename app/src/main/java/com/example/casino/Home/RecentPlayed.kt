package com.example.casino.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.R

@Composable
fun RecentPlayed(fontFamily: FontFamily) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Recent Played",
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            RecentGames(R.drawable.g1)
            RecentGames(R.drawable.g2)
            RecentGames(R.drawable.g3)
            RecentGames(R.drawable.g4)

        }
    }
}

@Composable
fun RecentGames(
    actionIcon: Int
) {
    Image(
        modifier = Modifier.size(50.dp).clip(RoundedCornerShape(50.dp)),
        painter = painterResource(actionIcon),
        contentDescription = "",
        contentScale = ContentScale.FillBounds
    )
}