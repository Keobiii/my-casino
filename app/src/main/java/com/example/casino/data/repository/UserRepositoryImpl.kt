package com.example.casino.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.casino.data.model.User
import com.example.casino.domain.repository.UserRepository
import com.example.casino.utils.AuthResponse
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepositoryImpl : UserRepository {
    private val auth = Firebase.auth
    private val database = FirebaseDatabase.getInstance().reference

    override fun createUserInDatabase(userId: String, user: User, onResult: (Boolean) -> Unit) {
        database.child("users").child(userId).setValue(user)
            .addOnCompleteListener{ task ->
                onResult(task.isSuccessful)
            }
    }

    override fun loginWithEmail(email: String, password: String, onResult: (AuthResponse) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    onResult(AuthResponse.Success)
                } else {
                    onResult(AuthResponse.Error(task.exception?.message ?: "Unknown Error"))
                }
            }
    }



    override fun observeUserData(uid: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        database.child("users").child(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    user?.let { liveData.postValue(it) }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("UserRepositoryImpl", "Failed to read user data", error.toException())
                }
            })
        return liveData
    }
}