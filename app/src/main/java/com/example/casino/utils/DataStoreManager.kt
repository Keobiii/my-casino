package com.example.casino.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val USER_UID_KEY = stringPreferencesKey("user_uid")
    }

    // Store UID
    suspend fun saveUserUid(uid: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_UID_KEY] = uid
        }
    }

    // Retrieve UID
    fun getUserUid(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[USER_UID_KEY]
        }
    }

    // Delete UID
    suspend fun clearUID() {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_UID_KEY)
        }
    }
}