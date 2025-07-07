package com.example.casino.presentation.ui.Shop

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.casino.data.repository.UserRepositoryImpl
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.TransactionHistoryUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.presentation.ui.Profile.userLoggedInUI
import com.example.casino.presentation.ui.Profile.userVisitorUI
import com.example.casino.presentation.viewmodel.BalanceViewModel
import com.example.casino.presentation.viewmodel.BalanceViewModelFactory
import com.example.casino.ui.theme.darkPageBackground
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.CustomOutlinedTextField
import com.example.casino.utils.DataStoreManager
import com.example.casino.utils.TextFieldWithDropdown
import com.example.casino.utils.UiState
import kotlinx.coroutines.delay

@Preview()
@Composable
fun Withdraw() {
    val context = LocalContext.current
    val dataStoreManager = DataStoreManager(context)

    val dataStoreManager2 = remember { DataStoreManager(context) }
    val uid by dataStoreManager2.getUserUid().collectAsState(initial = null)

    var amount by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf("Gcash") }

    val banks = listOf("Gcash", "Paymaya")

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

    val updateState = balanceViewModel.updateBalanceState


    LaunchedEffect(uid) {
        uid?.let {
            balanceViewModel.startObservingUser(it)
        }
    }

    LaunchedEffect(updateState) {
        if (updateState == UiState.Idle) return@LaunchedEffect

        when (updateState) {
            is UiState.Success -> {
                Toast.makeText(context, "Balance updated!", Toast.LENGTH_SHORT).show()
                amount = "";
            }
            is UiState.Error -> {
                Toast.makeText(context, updateState.message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }

        delay(1000)
        balanceViewModel.resetUpdateBalanceState()
    }






    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your current balance is:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))


                val userBalance = when (userState) {
                    is UiState.Loading -> "Loading..."
                    is UiState.Success -> "${userState.data.balance}"
                    is UiState.Error -> "Error"
                    else -> "Error"
                }


            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "PHP ${userBalance}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))
            

            TextFieldWithDropdown(
                label = "Enter amount",
                text = amount,
                onTextChange = { amount = it },
                dropdownItems = banks,
                selectedItem = selected,
                onItemSelected = { selected = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF2F2FFD))
                    .clickable {
                        val amountInput = amount.toDoubleOrNull()
                        if (amountInput == null || amountInput == 0.0) {
                            Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                            return@clickable
                        }

                        if (selected.isBlank()) {
                            Toast.makeText(context, "Please select a bank", Toast.LENGTH_SHORT).show()
                            return@clickable
                        }

                        if ( amountInput < 0 ) {
                            Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                            return@clickable
                        }

                        val withdrawAmount = amountInput * -1;

                        uid?.let { balanceViewModel.updateUserBalance(it, withdrawAmount) }
                    },
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Withdraw",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        if (updateState is UiState.Loading) {
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


@Composable
fun amountButton(
//    amount: Double
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(darkPageBackground),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 30.dp),
            text = "100",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}