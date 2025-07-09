package com.example.casino.data.repository

import com.example.casino.data.model.PaymentLinkResult
import com.example.casino.domain.repository.PaymentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class PaymentRepositoryImpl : PaymentRepository {
    private val client = OkHttpClient()

    override suspend fun createPaymentLink(
        amount: Int,
        description: String,
        onResult: (Result<PaymentLinkResult>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val mediaType = MediaType.parse("application/json")
                val body = RequestBody.create(
                    mediaType,
                    """
                    {
                      "data": {
                        "attributes": {
                          "amount": $amount,
                          "description": "$description"
                        }
                      }
                    }
                    """.trimIndent()
                )

                val request = Request.Builder()
                    .url("https://api.paymongo.com/v1/links")
                    .post(body)
                    .addHeader("accept", "application/json")
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", "Basic c2tfdGVzdF8yNlZVTHlvMlV2dlVEV2s4ank3d2ZhRzc6")
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body()?.string()

                if (response.isSuccessful && responseBody != null) {
                    val json = JSONObject(responseBody)
                    val attributes = json.getJSONObject("data").getJSONObject("attributes")
                    val checkoutUrl = attributes.getString("checkout_url")
                    val referenceNumber = attributes.getString("reference_number")

                    val result = PaymentLinkResult(
                        checkoutUrl = checkoutUrl,
                        referenceNumber = referenceNumber
                    )

                    onResult(Result.success(result))
                } else {
                    onResult(Result.failure(Exception("Failed: ${response.code()} - ${responseBody ?: "No response"}")))
                }
            } catch (e: Exception) {
                onResult(Result.failure(e))
            }
        }
    }

}
