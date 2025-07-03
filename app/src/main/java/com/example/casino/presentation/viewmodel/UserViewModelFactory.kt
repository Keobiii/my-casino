package com.example.casino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.casino.domain.usecase.ObserveUserDataUseCase

class UserViewModelFactory(
    private val observeUserDataUseCase: ObserveUserDataUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(observeUserDataUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}