package com.example.casino.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.casino.data.model.User
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase

class BalanceViewModel(
    private val observeUserDataUseCase: ObserveUserDataUseCase,
    private val updateUserFieldUseCase: UpdateUserFieldUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun startObservingUser(uid: String) {
        observeUserDataUseCase(uid).observeForever { userData ->
            _user.value = userData
        }
    }

    fun updateUserBalance(uid: String, delta: Int) {
        user.value?.let { currentUser ->
            val newBalance = currentUser.balance + delta
            val updates = mapOf("balance" to newBalance)

            updateUserFieldUseCase(uid, updates) { success ->
                if (!success) {
                    Log.e("BalanceViewModel", "Failed to update balance")
                }
            }
        }
    }



}
