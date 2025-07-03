package com.example.casino.presentation.ui.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.casino.R
import com.example.casino.data.model.Game
import com.example.casino.ui.theme.pageBackground

val games = listOf(
    Game(id = 1, title = "Game 1", description = "This is Game 1", creator = "Keobi K.", image = R.drawable.g1),
    Game(id = 2, title = "Game 2", description = "This is Game 2", creator = "Keobi K.", image = R.drawable.g2),
    Game(id = 3, title = "Game 3", description = "This is Game 3", creator = "Keobi K.", image = R.drawable.g3),
    Game(id = 4, title = "Game 4", description = "This is Game 4", creator = "Keobi K.", image = R.drawable.g4),
    Game(id = 5, title = "Game 5", description = "This is Game 5", creator = "Keobi K.", image = R.drawable.g5),
    Game(id = 6, title = "Game 6", description = "This is Game 6", creator = "Keobi K.", image = R.drawable.g6),
    Game(id = 7, title = "Game 7", description = "This is Game 7", creator = "Keobi K.", image = R.drawable.g7),
)


@Composable
fun Home(navController: NavHostController, fontFamily: FontFamily) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Carousel(fontFamily) }
            item { Spacer(modifier = Modifier.height(15.dp)) }
            item { RecentPlayed(fontFamily) }
            item { Spacer(modifier = Modifier.height(25.dp)) }
            item { GameCategories(navController, "Spin to Win", fontFamily, games) }
            item { Spacer(modifier = Modifier.height(25.dp)) }
            item { Games(navController, fontFamily, games) }
        }

    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Games(navController: NavHostController, fontFamily: FontFamily, game: List<Game>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Royal Games",
            fontFamily = fontFamily,
            fontSize = 16.sp,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.SpaceBetween,
            maxItemsInEachRow = 2,
            modifier = Modifier.fillMaxWidth()
        ) {
            game.forEach { gameItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.47f)
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(bottom = 15.dp)
                        .clickable {
                            Log.i("Game Clicked: ", gameItem.id.toString())
                            navController.navigate("gameUI/${gameItem.id}/${gameItem.title}")
                        }
                ) {
                    GameCard(gameItem)
                }
            }
        }
    }
}

@Composable
fun RandomImage(): Int {
    val RandomImage = remember { games.random().image }

    return RandomImage

}
@Composable
fun GameCard(gameItem: Game) {
    Box(
        modifier = Modifier
            .background(Color(0xFFF7F4F3)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = gameItem.image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F4F3)),
            contentScale = ContentScale.Crop
        )
    }
}




















