package com.example.casino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casino.data.model.PayMongoPayment
import com.example.casino.domain.repository.PaymentLogger
import com.example.casino.domain.usecase.CreatePaymentLinkUseCase
import kotlinx.coroutines.launch


class PaymentViewModel(
    private val createPaymentLinkUseCase: CreatePaymentLinkUseCase,
    private val logger: PaymentLogger
) : ViewModel() {
    fun createPayment(userId: String, amount: Int, description: String, onResult: (Result<String>) -> Unit) {
        viewModelScope.launch {
            createPaymentLinkUseCase(amount, description) { result ->
                result.onSuccess { (checkoutUrl, referenceNumber) ->
                    val payment = PayMongoPayment(
                        amount = amount,
                        description = description,
                        status = "unpaid",
                        referenceNumber = referenceNumber,
                        checkoutUrl = checkoutUrl
                    )
                    logger.logPayment(userId, payment) {}
                    onResult(Result.success(checkoutUrl))
                }
                result.onFailure {
                    onResult(Result.failure(it))
                }
            }
        }
    }

}