package com.example.casino.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.casino.data.model.User
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.utils.UiState

class BalanceViewModel(
    private val observeUserDataUseCase: ObserveUserDataUseCase,
    private val updateUserFieldUseCase: UpdateUserFieldUseCase
) : ViewModel() {

    var userState by mutableStateOf<UiState<User>>(UiState.Idle)
        private set

    var updateBalanceState by mutableStateOf<UiState<Unit>>(UiState.Idle)
        private set

    private var userObserver: Observer<User>? = null

    fun startObservingUser(uid: String) {
        userState = UiState.Loading

        userObserver?.let {
            observeUserDataUseCase(uid).removeObserver(it)
        }

        userObserver = Observer { user ->
            if (user != null) {
                userState = UiState.Success(user)
            } else {
                userState = UiState.Error("User not found")
            }
        }

        observeUserDataUseCase(uid).observeForever(userObserver!!)
    }

    fun updateUserBalance(uid: String, delta: Double) {
        val user = (userState as? UiState.Success)?.data ?: return

        val newBalance = user.balance + delta
        val updates = mapOf("balance" to newBalance)

        updateBalanceState = UiState.Loading

        updateUserFieldUseCase(uid, updates) { success ->
            updateBalanceState = if (success) {
                UiState.Success(Unit)
            } else {
                UiState.Error("Failed to update balance")
            }
        }
    }

    fun resetUpdateBalanceState() {
        updateBalanceState = UiState.Idle
    }

    override fun onCleared() {
        userObserver?.let {
            observeUserDataUseCase("").removeObserver(it)
        }
        super.onCleared()
    }
}
