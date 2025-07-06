package com.example.casino.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.casino.data.model.User
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.TransactionHistoryUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.utils.UiState

class BalanceViewModel(
    private val observeUserDataUseCase: ObserveUserDataUseCase,
    private val updateUserFieldUseCase: UpdateUserFieldUseCase,
    private val transactionHistoryUseCase: TransactionHistoryUseCase
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
            userState = if (user != null) {
                UiState.Success(user)
            } else {
                UiState.Error("User not found")
            }
        }

        observeUserDataUseCase(uid).observeForever(userObserver!!)
    }

    fun updateUserBalance(uid: String, delta: Double) {
        val user = (userState as? UiState.Success)?.data ?: return

        val currentBalance = user.balance
        val newBalance = currentBalance + delta

        if (newBalance < 0) {
            updateBalanceState = UiState.Error("Insufficient balance")
            return
        }

        updateBalanceState = UiState.Loading

        val updates = mapOf("balance" to newBalance)

        updateUserFieldUseCase(uid, updates) { balanceSuccess ->
            if (balanceSuccess) {
                transactionHistoryUseCase(uid, delta) { historySuccess ->
                    updateBalanceState = if (historySuccess) {
                        UiState.Success(Unit)
                    } else {
                        UiState.Error("Balance updated, but failed to log transaction.")
                    }
                }
            } else {
                updateBalanceState = UiState.Error("Failed to update balance.")
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
