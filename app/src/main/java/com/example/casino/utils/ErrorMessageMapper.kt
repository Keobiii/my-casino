package com.example.casino.utils

object ErrorMessageMapper {
    fun map(message: String): String {
        return when {
            message.contains("The supplied auth credential is incorrect", ignoreCase = true) ->
                "Incorrect email or password. Please try again."

            message.contains("There is no user record", ignoreCase = true) ->
                "No account found with this email."

            message.contains("email address is badly formatted", ignoreCase = true) ->
                "Invalid email address format."

            message.contains("password is invalid", ignoreCase = true) ->
                "Incorrect password. Please try again."

            message.contains("already in use", ignoreCase = true) ->
                "This email is already registered."

            message.contains("network error", ignoreCase = true) ->
                "Network error. Check your internet connection."

            else -> "Something went wrong. Please try again."
        }
    }
}
