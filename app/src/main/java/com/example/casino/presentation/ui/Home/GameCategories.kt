package com.example.casino.presentation.ui.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import com.example.casino.data.model.Game

@Composable
fun GameCategories(
    navController: NavHostController,
    title: String,
    fontFamily: FontFamily,
    games: List<Game>
) {
    Column(
        modifier = Modifier.fillMaxWidth().height(220.dp).padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        LazyRow {
            items(games.size) { index ->
                GamesList(index, games, navController, fontFamily)
            }
        }
    }
}

@Composable
fun GamesList(
    index: Int,
    games: List<Game>,
    navController: NavHostController,
    fontFamily: FontFamily
) {
    val game = games[index]
    val lastItemPaddingEnd = if (index == games.size - 1) 0.dp else 16.dp

    Box(modifier = Modifier.padding( end = lastItemPaddingEnd)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .width(110.dp)
                .fillMaxHeight(1f)
                .clickable {
                    Log.i("Game Index: ", index.toString())
                    navController.navigate("gameUI/${game.id}/${game.title}")
                },
        ) {
            Image(
                painter = painterResource(id = game.image),
                contentDescription = game.title,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F4F3)),
                contentScale = ContentScale.Crop
            )
        }
    }
}