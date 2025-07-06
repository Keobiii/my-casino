package com.example.casino.domain.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class TransactionHistoryUseCase (
    private val updateUserFieldUseCase: UpdateUserFieldUseCase
) {
    operator fun invoke(
        uid: String,
        delta: Double,
        onResult: (Boolean) -> Unit
    ) {
        val type = if (delta > 0) "cash-in" else "cash-out"
        val now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        val historyPath = if (delta > 0) "cashInHistory" else "cashOutHistory"
        val transactionId = UUID.randomUUID().toString()

        val transaction = mapOf(
            "transactionId" to transactionId,
            "date" to now,
            "type" to type,
            "amount" to delta
        )

        // ⚠️ Firebase path structure: key.subkey -> value
        val updates = mapOf("$historyPath/$transactionId" to transaction)

        updateUserFieldUseCase(uid, updates, onResult)
    }
}