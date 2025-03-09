package com.example.casino.TopUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.Home.getGradient
import com.example.casino.R
import com.example.casino.data.Transaction
import com.example.casino.ui.theme.ch4EndColor
import com.example.casino.ui.theme.ch4StartColor
import com.example.casino.ui.theme.eggGray
import com.example.casino.ui.theme.eggWhite
import com.example.casino.ui.theme.pageBackground

val transactions = listOf(
    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = Icons.Default.Add
    ),

    Transaction(
        type = "withdraw",
        date = "11/12/25",
        amount = 24.25f,
        icon = Icons.Default.CreditCard
    ),

    Transaction(
        type = "withdraw",
        date = "11/12/25",
        amount = 24.25f,
        icon = Icons.Default.CreditCard
    ),

    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = Icons.Default.CreditCard
    ),

    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = Icons.Default.CreditCard
    ),

    Transaction(
        type = "cash_in",
        date = "11/12/25",
        amount = 24.25f,
        icon = Icons.Default.CreditCard
    ),
)


@Composable
fun TopUp(fontFamily: FontFamily) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BalanceUI(fontFamily)
            Spacer(modifier = Modifier.height(20.dp))
            QuickAction(fontFamily)
            Spacer(modifier = Modifier.height(20.dp))
            History(fontFamily)
        }
    }
}

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.linearGradient(
        colors = listOf(startColor, endColor)
    )
}

@Composable
fun BalanceUI(fontFamily: FontFamily) {
    var isVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp)),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(160.dp)
                .background(getGradient(ch4StartColor, ch4EndColor))
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Background Image
                Image(
                    painter = painterResource(id = R.drawable.ch4),
                    contentDescription = "Character Png",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterEnd)
                        .width(130.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Account Balance",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            fontFamily = fontFamily
                        )

                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Setting Icon",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (isVisible) "$ 1,500.00" else "$ ****",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Start,
                        fontFamily = fontFamily
                    )

                    Icon(
                        modifier = Modifier.size(20.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isVisible = !isVisible
                            },
                        imageVector = if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Eye Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

    }
}


@Composable
fun QuickAction(fontFamily: FontFamily) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Quick Actions",
            fontSize = 18.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ActionsIU(fontFamily, Icons.Default.Add, "Cash In")
            ActionsIU(fontFamily, Icons.Default.Payment, "Cash Out")
            ActionsIU(fontFamily, Icons.Default.CurrencyExchange, "History")
            ActionsIU(fontFamily, Icons.Default.QrCodeScanner, "Scan")

        }
    }
}

@Composable
fun ActionsIU(
    fontFamily: FontFamily,
    actionIcon: ImageVector,
    actionTitle: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(5.dp)
        ) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = actionIcon,
                contentDescription = actionTitle,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = actionTitle,
            fontSize = 12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun History(fontFamily: FontFamily) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Transactions",
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "See All",
                fontFamily = fontFamily,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End,
                textDecoration = TextDecoration.Underline,
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
fun HistoryList(index: Int, fontFamily: FontFamily) {


    val bgColor = if (index % 2 == 0) {
        eggGray
    } else {
        eggWhite
    }

    val transaction = transactions[index]

    val (title, fontColor) = when (transaction.type) {
        "cash_in" -> "Cash In" to Color.Green
        else -> "Withdraw" to Color.Red
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
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

                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = transaction.icon,
                        contentDescription = transaction.type,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = title,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                    Text(
                        text = transaction.date,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
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
                    fontSize = 12.sp,
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
