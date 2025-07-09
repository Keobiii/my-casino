package com.example.casino.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casino.domain.usecase.CreatePaymentLinkUseCase
import com.example.casino.utils.UiState
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val createPaymentLinkUseCase: CreatePaymentLinkUseCase
) : ViewModel() {

    fun createPayment(amount: Int, description: String, onResult: (Result<String>) -> Unit) {
        viewModelScope.launch {
            createPaymentLinkUseCase(amount, description, onResult)
        }
    }
}
