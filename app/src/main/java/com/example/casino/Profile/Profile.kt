package com.example.casino.Profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.R
import com.example.casino.ui.theme.pageBackground

@Composable
fun Profile(fontFamily: FontFamily) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(pageBackground)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().height(260.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

//                ProfileBackground()
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(4.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.nobita),
                        contentDescription = "User Icon"
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = "Keobi Keribu",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Player",
                    fontSize = 14.sp,
                    color = Color.White,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )

            }

            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // Profile
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(0.8f).padding(start = 16.dp).fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "Person Icon",
                            tint = Color.White
                        )


                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = "Language",
                            color = Color.White,
                            fontFamily = fontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }

                    Column(
                        modifier = Modifier.weight(0.15f).fillMaxSize().padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "Arrow Icon",
                            tint = Color.White
                        )

                    }
                }

                // Email
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(0.8f).padding(start = 16.dp).fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "Email Icon",
                            tint = Color.White
                        )


                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = "Linked Account",
                            color = Color.White,
                            fontFamily = fontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )


                    }

                    Column(
                        modifier = Modifier.weight(0.15f).fillMaxSize().padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "Arrow Icon",
                            tint = Color.White
                        )

                    }
                }

                // Clear Cache
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(0.8f).padding(start = 16.dp).fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Rounded.DeleteOutline,
                            contentDescription = "Cache Icon",
                            tint = Color.White
                        )


                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = "Clear Cache",
                            color = Color.White,
                            fontFamily = fontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )


                    }

                    Column(
                        modifier = Modifier.weight(0.15f).fillMaxSize().padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "Arrow Icon",
                            tint = Color.White
                        )

                    }
                }

                // Clear History
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(0.8f).padding(start = 16.dp).fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Rounded.History,
                            contentDescription = "History Icon",
                            tint = Color.White
                        )


                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = "Clear History",
                            color = Color.White,
                            fontFamily = fontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )


                    }

                    Column(
                        modifier = Modifier.weight(0.15f).fillMaxSize().padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "Arrow Icon",
                            tint = Color.White
                        )

                    }
                }

                // Logout
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                            (context as? Activity)?.finish()
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier.weight(0.8f).padding(start = 16.dp).fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.AutoMirrored.Rounded.Logout,
                            contentDescription = "Logout Icon",
                            tint = Color.White
                        )


                        Text(
                            modifier = Modifier.padding(start = 25.dp),
                            text = "Logout",
                            color = Color.White,
                            fontFamily = fontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }

                    Column(
                        modifier = Modifier.weight(0.15f).fillMaxSize().padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = "Arrow Icon",
                            tint = Color.White
                        )

                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun ProfileBackground() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }

//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Box(
//            modifier = Modifier.clip(RoundedCornerShape(50.dp))
//                .background(MaterialTheme.colorScheme.onBackground)
//                .padding(4.dp)
//        ) {
//            Image(
//                modifier = Modifier
//                    .size(90.dp)
//                    .clip(CircleShape),
//                painter = painterResource(id = R.drawable.nobita),
//                contentDescription = "User Icon"
//            )
//        }
//        Text(
//            modifier = Modifier.padding(top = 15.dp),
//            text = "Keobi Keribu",
//            fontSize = 18.sp,
//            color = Color.White,
//            fontWeight = FontWeight.Bold
//        )
//
//        Text(
//            text = "Player",
//            fontSize = 14.sp,
//            color = Color.White,
//            fontWeight = FontWeight.Normal
//        )
//    }
}














