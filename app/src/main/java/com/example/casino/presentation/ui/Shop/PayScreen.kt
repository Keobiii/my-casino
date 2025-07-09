package com.example.casino.presentation.ui.Shop

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.casino.data.repository.PaymentRepositoryImpl
import com.example.casino.domain.usecase.CreatePaymentLinkUseCase
import com.example.casino.presentation.viewmodel.PaymentViewModel
import com.example.casino.utils.UiState

@Composable
fun PayScreen() {
    val context = LocalContext.current

    // Setup ViewModel manually
    val viewModel = remember {
        PaymentViewModel(
            CreatePaymentLinkUseCase(PaymentRepositoryImpl())
        )
    }

    var amount by remember { mutableStateOf("200") }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Enter amount (PHP)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val centavos = ((amount.toDoubleOrNull() ?: 0.0) * 100).toInt()
                if (centavos > 0) {
                    isLoading = true
                    error = null
                    viewModel.createPayment(centavos, "Payment") { result ->
                        isLoading = false
                        result.onSuccess { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }.onFailure {
                            error = it.message ?: "Something went wrong"
                        }
                    }
                } else {
                    error = "Please enter a valid amount"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Pay Now")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        error?.let {
            Text(
                text = it,
                color = Color.Red
            )
        }
    }
}
