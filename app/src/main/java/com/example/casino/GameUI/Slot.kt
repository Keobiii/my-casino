package com.example.casino.GameUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.casino.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun WheelSlot() {
    val images = listOf(
        R.drawable.g1,
        R.drawable.g2,
        R.drawable.g3,
        R.drawable.g4,
        R.drawable.g5,
        R.drawable.g6,
    )

    val listState = rememberLazyListState()
    var selectedIndex by remember { mutableStateOf(1) } // Start in the middle
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(300.dp) // Ensures only 3 images are visible
                .clip(RectangleShape)
                .border(2.dp, Color.Gray)
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(images.size) { index ->
                    Image(
                        painter = painterResource(id = images[index]),
                        contentDescription = "Image $index",
                        modifier = Modifier
                            .size(if (index == selectedIndex) 120.dp else 80.dp) // Scale selected image
                            .padding(8.dp)
                            .alpha(if (index == selectedIndex) 1f else 0.5f) // Fade effect
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            coroutineScope.launch {
                val randomIndex = (0 until images.size).random()
                val spinRounds = 5 // Number of full spins before stopping
                val finalIndex = spinRounds * images.size + randomIndex // Ensure multiple spins

                for (i in listState.firstVisibleItemIndex until finalIndex) {
                    listState.scrollToItem(i % images.size) // Loop through images
                    delay((50 + i * 3).toLong()) // Gradually slow down
                }

                selectedIndex = randomIndex
                listState.animateScrollToItem(randomIndex)
            }
        }) {
            Text("Spin Wheel")
        }
    }
}

