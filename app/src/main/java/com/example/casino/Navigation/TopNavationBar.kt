package com.example.casino.Navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.casino.R
import com.example.casino.ui.theme.blueViolet
import com.example.casino.ui.theme.pageBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navController: NavController,
    fontFamily: FontFamily
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coin))

    TopAppBar(
        title = { Spacer(modifier = Modifier) },
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier.weight(0.3f)
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.coin),
//                        contentDescription = "Company Logo",
//                        modifier = Modifier
//                            .size(35.dp)
//                            .clickable { navController.navigate("home") }
//                    )
                    Box(
                        modifier = Modifier.size(35.dp)
                    ) {
                        LottieAnimation(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    navController.navigate("home")
                                },
                            composition = composition,
                            isPlaying = true,
                            iterations = LottieConstants.IterateForever,
                        )
                    }
                }

                Row(
                    modifier = Modifier.weight(0.4f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Top-Up Button
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(15.dp))
                    ){
                        Row(
                            modifier = Modifier
                                .height(33.dp)
                                .width(130.dp)
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                            ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "$ 1,500.00",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontFamily,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Box(
                                modifier = Modifier.clip(RoundedCornerShape(50.dp))
                                    .background(blueViolet)
                            ) {

                                Icon(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(3.dp)
                                        .clickable {
                                            navController.navigate("top_up")
                                        },
                                    imageVector = Icons.Rounded.Add,
                                    contentDescription = "Add Icon",
                                    tint = Color.White,
                                )
                            }

                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Profile Button
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(50.dp))
                            .background(MaterialTheme.colorScheme.onBackground)
                            .padding(4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .clickable {
                                    navController.navigate("profile")
                                },
                            painter = painterResource(id = R.drawable.nobita),
                            contentDescription = "User Icon",
                        )
                    }
                }


            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = pageBackground, // Background color
            titleContentColor = Color.White
        )
    )
}
