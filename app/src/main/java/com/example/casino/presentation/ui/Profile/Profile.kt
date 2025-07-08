package com.example.casino.presentation.ui.Profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.casino.R
import com.example.casino.data.repository.UserRepositoryImpl
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.TransactionHistoryUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.presentation.ui.Authentication.LoginActivity
import com.example.casino.presentation.viewmodel.BalanceViewModel
import com.example.casino.presentation.viewmodel.BalanceViewModelFactory
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.DataStoreManager
import com.example.casino.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Profile(fontFamily: FontFamily) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStoreManager = DataStoreManager(context)
    val uidFlow = dataStoreManager.getUserUid()

    val dataStoreManager2 = remember { DataStoreManager(context) }
    val uid by dataStoreManager2.getUserUid().collectAsState(initial = null)

    // Initialize ViewModel
    val repository = UserRepositoryImpl()
    val updateUserFieldUseCase = UpdateUserFieldUseCase(repository)
    val balanceViewModel: BalanceViewModel = viewModel(
        factory = BalanceViewModelFactory(
            observeUserDataUseCase = ObserveUserDataUseCase(repository),
            updateUserFieldUseCase = UpdateUserFieldUseCase(repository),
            transactionHistoryUseCase = TransactionHistoryUseCase(updateUserFieldUseCase)
        )
    )

    val userState = balanceViewModel.userState

    LaunchedEffect(uid) {
        uid?.let {
            balanceViewModel.startObservingUser(it)
        }
    }

    LaunchedEffect(Unit) {
        uidFlow.collect { uid ->
            if (uid != null) {
                Toast.makeText(context, "USER LOGGED IN: $uid", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "USER LOGGED IN: null", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(pageBackground),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (uid != null) {
                val userEmail = when (userState) {
                    is UiState.Loading -> "Loading..."
                    is UiState.Success -> "${userState.data.email}"
                    is UiState.Error -> "Error occur! Please reload or re-login"
                    else -> ""
                }
                userLoggedInUI(fontFamily, coroutineScope, context, dataStoreManager, userEmail)
            } else  {
                userVisitorUI(fontFamily, context)
            }
        }
    }
}

@Composable
fun userVisitorUI(
    fontFamily: FontFamily,
    context: Context
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                (context as? Activity)?.finish()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
        ) {
            Text(
                text = "Login First",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily
            )
        }
    }

}

@Composable
fun userLoggedInUI(
    fontFamily: FontFamily,
    coroutineScope: CoroutineScope,
    context: Context,
    dataStoreManager: DataStoreManager,
    userEmail: String,
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
            text = "${userEmail}",
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

        // Logout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    coroutineScope.launch {
                        dataStoreManager.clearUID()

                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    }
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














