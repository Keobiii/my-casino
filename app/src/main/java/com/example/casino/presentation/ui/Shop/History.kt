package com.example.casino.presentation.ui.Shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.casino.R
import com.example.casino.data.model.Transaction
import com.example.casino.data.model.Transaction_Model
import com.example.casino.data.repository.UserRepositoryImpl
import com.example.casino.domain.usecase.GetTransactionHistoryUseCase
import com.example.casino.presentation.viewmodel.TransactionViewModel
import com.example.casino.presentation.viewmodel.TransactionViewModelFactory
import com.example.casino.utils.DataStoreManager

@Composable
fun History(fontFamily: FontFamily) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val uid by dataStoreManager.getUserUid().collectAsState(initial = null)

    val repository = UserRepositoryImpl()
    val transactionViewModel: TransactionViewModel = viewModel(
        factory = TransactionViewModelFactory(GetTransactionHistoryUseCase(repository))
    )

    LaunchedEffect(uid) {
        uid?.let { transactionViewModel.loadTransactionHistory(it) }
    }

    val transactions = transactionViewModel.transactionList

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Transactions",
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        LazyColumn(
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ) {
            items(transactions) { transaction ->
                HistoryList(transaction, fontFamily)
            }
        }
    }
}

@Composable
fun HistoryList(transaction: Transaction, fontFamily: FontFamily) {
    val (title, fontColor, iconRes) = when (transaction.type) {
        "cash_in" -> Triple("Cash In", Color.Green, R.drawable.coin_cash_in)
        else -> Triple("Withdraw", Color.Red, R.drawable.coin_cash_out)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(0.6f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = transaction.type,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(4.dp)
                )

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = title, fontFamily = fontFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Color.White)
                    Text(text = transaction.date, fontFamily = fontFamily, fontSize = 14.sp, color = Color.White)
                }
            }

            Text(
                text = if (transaction.type == "cash_in") "${transaction.amount}" else "${transaction.amount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = fontColor,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.4f)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray))
    }
}


