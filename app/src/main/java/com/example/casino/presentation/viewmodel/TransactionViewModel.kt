package com.example.casino.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.casino.data.model.Transaction
import com.example.casino.domain.usecase.GetTransactionHistoryUseCase
import com.example.casino.utils.UiState

class TransactionViewModel(
    private val getTransactionHistoryUseCase: GetTransactionHistoryUseCase
) : ViewModel() {

    var transactionList by mutableStateOf<List<Transaction>>(emptyList())
        private set

    fun loadTransactionHistory(uid: String) {
        getTransactionHistoryUseCase(uid) {
            transactionList = it
        }
    }
}
