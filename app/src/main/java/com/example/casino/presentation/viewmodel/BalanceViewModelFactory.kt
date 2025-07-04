package com.example.casino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase

class BalanceViewModelFactory(
    private val observeUserDataUseCase: ObserveUserDataUseCase,
    private val updateUserFieldUseCase: UpdateUserFieldUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BalanceViewModel::class.java)) {
            return BalanceViewModel(observeUserDataUseCase, updateUserFieldUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
