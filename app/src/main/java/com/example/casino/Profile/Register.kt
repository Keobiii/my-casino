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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.MainActivity
import com.example.casino.R
import com.example.casino.elements.CustomOutlinedTextField
import com.example.casino.ui.theme.eggWhite
import com.example.casino.ui.theme.pageBackground

@Preview
@Composable
fun Register() {
    val context = LocalContext.current
    var emailField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }
    var cpasswordField by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.coin_3d),
                contentDescription = "Dice Icon",
                alignment = Alignment.CenterEnd,
                modifier = Modifier.size(150.dp)
            )

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CustomOutlinedTextField(
                    value = emailField,
                    onValueChange = { emailField = it },
                    label = "Email",
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomOutlinedTextField(
                    value = passwordField,
                    onValueChange = { passwordField = it },
                    label = "Password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomOutlinedTextField(
                    value = cpasswordField,
                    onValueChange = { cpasswordField = it },
                    label = "Confirm Password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(10.dp))



                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Register",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

//                Button(
//                    onClick = {},
//                    colors = ButtonDefaults.buttonColors(containerColor = eggWhite),
//                    shape = RoundedCornerShape(10.dp),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//
//                        Image(
//                            painter = painterResource(id = R.drawable.google_icon),
//                            contentDescription = "Google Icon",
//                            modifier = Modifier
//                                .height(25.dp)
//                                .padding(end = 10.dp)
//                        )
//
//                        Text(
//                            text = "Continue with Google",
//                            textAlign = TextAlign.Center,
//                            fontSize = 16.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Bold,
//                        )
//                    }
//                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "You have already an Accountt? Login",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                            (context as? Activity)?.finish()
                        }
                )

//                Button(
//                    onClick = {},
//                    colors = ButtonDefaults.buttonColors(containerColor = eggWhite),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(20.dp))
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//
//                        Image(
//                            painter = painterResource(id = R.drawable.facebook_icon),
//                            contentDescription = "Facebook Icon",
//                            modifier = Modifier.height(25.dp).padding(end = 10.dp)
//                        )
//
//                        Text(
//                            text = "Continue with Facebook",
//                            textAlign = TextAlign.Center,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Bold,
//                        )
//                    }
//                }

            }
        }
    }
}