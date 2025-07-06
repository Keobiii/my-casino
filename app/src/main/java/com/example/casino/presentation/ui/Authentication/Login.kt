package com.example.casino.presentation.ui.Authentication

import android.app.Activity
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.MainActivity
import com.example.casino.R
import com.example.casino.data.repository.AuthRepositoryImpl
import com.example.casino.domain.usecase.LoginUserUseCase
import com.example.casino.presentation.viewmodel.LoginViewModel
import com.example.casino.utils.CustomOutlinedTextField
import com.example.casino.ui.theme.eggWhite
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.DataStoreManager
import com.example.casino.utils.ErrorMessageMapper
import com.example.casino.utils.UiState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.delay

@Composable
fun Login() {
    val context = LocalContext.current
    val viewModel = remember {
        LoginViewModel(LoginUserUseCase(AuthRepositoryImpl()))
    }

    var emailField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }

    val loginState = viewModel.loginState

    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is UiState.Success -> {
                val uid = Firebase.auth.currentUser?.uid
                uid?.let {
                    DataStoreManager(context).saveUserUid(it)
                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                    (context as? Activity)?.finish()
                }
            }

            is UiState.Error -> {
                Toast.makeText(context, ErrorMessageMapper.map(state.message), Toast.LENGTH_SHORT).show()
            }

//            is UiState.Loading -> {
//                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
//            }

            else -> {}
        }

        // Delay resting state to see the loading
        if (loginState !is UiState.Loading) {
            delay(1000)
            viewModel.resetLoginState()
        }
    }



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

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Forgot Password?",
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {
                        if (emailField.isNotBlank() &&  passwordField.isNotBlank()) {
                            viewModel.login(emailField, passwordField)
                        } else {
                            Toast.makeText(context, "Fill up all fields", Toast.LENGTH_SHORT).show()
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Login",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = eggWhite),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "Google Icon",
                            modifier = Modifier
                                .height(25.dp)
                                .padding(end = 10.dp)
                        )

                        Text(
                            text = "Continue with Google",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "You don't have an Account? Register",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, RegisterActivity::class.java)
                            context.startActivity(intent)
                            (context as? Activity)?.finish()
                        }
                )

            }
        }

        if (loginState is UiState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}
