package com.example.casino.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.casino.data.model.User
import com.example.casino.domain.usecase.ObserveUserDataUseCase

class UserViewModel(
    private val observeUserDataUseCase: ObserveUserDataUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun startObservingUser(uid: String) {
        observeUserDataUseCase(uid).observeForever { userData ->
            _user.value = userData
        }
    }
}
