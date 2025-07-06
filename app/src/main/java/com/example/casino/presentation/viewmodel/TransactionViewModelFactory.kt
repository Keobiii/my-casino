package com.example.casino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.casino.domain.usecase.GetTransactionHistoryUseCase

class TransactionViewModelFactory(
    private val getTransactionHistoryUseCase: GetTransactionHistoryUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            return TransactionViewModel(getTransactionHistoryUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}