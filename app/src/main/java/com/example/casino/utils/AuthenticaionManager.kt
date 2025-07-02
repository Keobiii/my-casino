package com.example.casino.utils

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.casino.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.security.MessageDigest
import java.util.UUID

class AuthenticaionManager(val context: Context) {
    private val auth = Firebase.auth

    // function to create account
    // 1. Using Email
    fun createAccountWithEmail(email: String, password: String) : Flow<AuthResponse> = callbackFlow {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        trySend(AuthResponse.Success)
                        close()
                    } else {
                        trySend(AuthResponse.Error(messsage = task.exception?.message ?: "Unknown Error"))
                        close()
                    }
                }
        } catch (e: Exception) {
            trySend(AuthResponse.Error(e.message ?: "Unknown error"))
            close()
        }


        awaitClose()
    }

    fun loginWithEmail(email: String, password: String): Flow<AuthResponse> = callbackFlow {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        trySend(AuthResponse.Success)
                        close()
                    } else {
                        trySend(AuthResponse.Error(messsage = task.exception?.message ?: "Unknown Error"))
                        close()
                    }
                }
        } catch (e: Exception) {
            trySend(AuthResponse.Error(e.message ?: "Unknown error"))
            close()
        }


        awaitClose()
    }

    private fun createNonce(): String {
        val rawNone = UUID.randomUUID().toString()
        val bytes = rawNone.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it -> str + "%02x".format(it)}
    }

//    fun signInWithGoogle(): Flow<AuthResponse> = callbackFlow {
//        val googleIdOption = GetGoogleIdOption.Builder()
//            .setFilterByAuthorizedAccounts(false)
//            .setServerClientId(context.getString(R.string.web_client_id))
//            .setAutoSelectEnabled(false)
//            .setNonce(createNonce())
//            .build()
//
//
//        val request = GetCredentialRequest.Builder()
//            .addCredentialOption(googleIdOption)
//            .build()
//
//        try {
//            val credentialManager = CredentialManager.create(context)
//            val result = credentialManager.getCredential(context = context, request = request )
//
//            val credential = result.credential
//
//            if (credential is CustomCredential) {
//                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL) {
//                    try {
//                        val googleIdTokenCredential = GoogleIdTokenCredential
//                            .createFrom(credential.data)
//
//                        val firebaseCredential = GoogleAuthProvider
//                            .getCredential(
//                                googleIdTokenCredential.idToken,
//                                null
//                            )
//
//                        auth.signInWithCredential(firebaseCredential)
//                            .addOnCompleteListener {
//                                if (it.isSuccessful) {
//                                    trySend(AuthResponse.Success)
//                                } else {
//                                    trySend(AuthResponse.Error(messsage = it.exception?.message ?: ""))
//                                }
//                            }
//                    } catch (e: GoogleIdTokenParsingException) {
//                        trySend(AuthResponse.Error(messsage = e.message ?: ""))
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            trySend(AuthResponse.Error(e.message ?: "Google sign-in failed"))
//        }
//
//        awaitClose()
//    }

    fun signInWithGoogle(): Flow<AuthResponse> = callbackFlow {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setAutoSelectEnabled(false)
            .setNonce(createNonce())
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(context = context, request = request)
            val credential = result.credential

            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL) {

                try {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                    val firebaseCredential = GoogleAuthProvider.getCredential(
                        googleIdTokenCredential.idToken,
                        null
                    )

                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                trySend(AuthResponse.Success)
                            } else {
                                trySend(
                                    AuthResponse.Error(
                                        task.exception?.message ?: "Firebase sign-in failed"
                                    )
                                )
                            }

                            // Safely close the flow after sending
                            close()
                        }

                } catch (e: GoogleIdTokenParsingException) {
                    trySend(AuthResponse.Error(e.message ?: "Token parsing failed"))
                    close()
                }
            } else {
                trySend(AuthResponse.Error("Invalid Google credentials"))
                close()
            }

        } catch (e: Exception) {
            if (e.message?.contains("cancel", true) == true) {
                trySend(AuthResponse.Error("Sign-in cancelled by user"))
            } else {
                trySend(AuthResponse.Error(e.message ?: "Google sign-in failed"))
            }
            close()
        }

        awaitClose { /* clean up if needed */ }
    }

}

interface AuthResponse {
    data object Success: AuthResponse
    data class Error(val messsage: String) : AuthResponse
}