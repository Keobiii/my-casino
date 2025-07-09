package com.example.casino.data.repository

import com.example.casino.data.model.PayMongoPayment
import com.example.casino.domain.repository.PaymentLogger
import com.google.firebase.database.FirebaseDatabase

class FirebasePaymentLogger : PaymentLogger {
    private val db = FirebaseDatabase.getInstance().getReference("payments")

    override fun logPayment(userId: String, payment: PayMongoPayment, onComplete: (Boolean) -> Unit) {
        val paymentId = payment.referenceNumber
        db.child(userId).child(paymentId).setValue(payment)
            .addOnCompleteListener { task -> onComplete(task.isSuccessful) }
    }

    override fun updatePaymentStatus(userId: String, referenceNumber: String, status: String) {
        db.child(userId).child(referenceNumber).child("status").setValue(status)
    }

}
