package com.example.casino.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.R
import com.example.casino.presentation.ui.Home.getGradient
import com.example.casino.presentation.viewmodel.BalanceViewModel
import com.example.casino.ui.theme.ch4EndColor
import com.example.casino.ui.theme.ch4StartColor

@Composable
fun UnusedUI() {

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
fun QuickAction(
    fontFamily: FontFamily,
    balanceViewModel: BalanceViewModel,
    uid: String?
) {
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
            horizontalArrangement = Arrangement.SpaceAround
        ) {

//            ActionsIU(fontFamily, R.drawable.coin_cash_in, "Cash In", {
//                uid?.let { balanceViewModel.updateUserBalance(it, 100) }
//            })
//            ActionsIU(fontFamily, R.drawable.coin_cash_out, "Cash Out", {
//                uid?.let { balanceViewModel.updateUserBalance(it, -10) }
//            })
//            ActionsIU(fontFamily, Icons.Default.CurrencyExchange, "History", {})
//            ActionsIU(fontFamily, Icons.Default.QrCodeScanner, "Scan", {})

        }
    }
}

@Composable
fun ActionsIU(
    fontFamily: FontFamily,
    icon: Int,
    actionTitle: String,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(45.dp))
                .background(MaterialTheme.colorScheme.background)
                .clickable { onClick() }
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = actionTitle,
                modifier = Modifier.size(30.dp)
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