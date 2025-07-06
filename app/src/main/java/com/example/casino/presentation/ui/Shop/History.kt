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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.R
import com.example.casino.data.model.Transaction
import com.example.casino.ui.theme.eggGray
import com.example.casino.ui.theme.eggWhite

val transactions = listOf(
    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = R.drawable.coin_cash_in
    ),

    Transaction(
        type = "withdraw",
        date = "11/12/25",
        amount = 24.25f,
        icon = R.drawable.coin_cash_out
    ),

    Transaction(
        type = "withdraw",
        date = "11/12/25",
        amount = 24.25f,
        icon = R.drawable.coin_cash_out
    ),

    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = R.drawable.coin_cash_in
    ),

    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = R.drawable.coin_cash_in
    ),

    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = R.drawable.coin_cash_in
    ),
)

@Composable
fun History(
    fontFamily: FontFamily
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Transactions",
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        LazyColumn(
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ) {
            items(transactions.size) { index ->
                HistoryList(index, fontFamily)
            }

        }

    }
}

@Composable
fun HistoryList(
    index: Int,
    fontFamily: FontFamily
) {

    val transaction = transactions[index]
    val (title, fontColor) = when (transaction.type) {
        "cash_in" -> "Cash In" to Color.Green
        else -> "Withdraw" to Color.Red
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = transaction.icon),
                        contentDescription = transaction.type,
                        modifier = Modifier.size(30.dp),

                        )
                }

                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = title,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White,
                    )
                    Text(
                        text = transaction.date,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                }


            }

            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(end = 5.dp),
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (transaction.type == "cash_in") "+$${transaction.amount}" else "-$${transaction.amount}",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = fontColor,
                    textAlign = TextAlign.End
                )
            }


        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray))
    }


}