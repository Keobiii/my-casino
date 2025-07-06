package com.example.casino.presentation.ui.Shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.casino.presentation.ui.Home.getGradient
import com.example.casino.R
import com.example.casino.data.model.TopUp
import com.example.casino.data.repository.UserRepositoryImpl
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.presentation.viewmodel.BalanceViewModel
import com.example.casino.presentation.viewmodel.BalanceViewModelFactory
import com.example.casino.ui.theme.ch4EndColor
import com.example.casino.ui.theme.ch4StartColor
import com.example.casino.ui.theme.darkPageBackground
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.DataStoreManager


@Composable
fun TopUp(
    fontFamily: FontFamily
) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    val uid by dataStoreManager.getUserUid().collectAsState(initial = null)

    val repository = UserRepositoryImpl()
    val balanceViewModel: BalanceViewModel = viewModel(
        factory = BalanceViewModelFactory(
            observeUserDataUseCase = ObserveUserDataUseCase(repository),
            updateUserFieldUseCase = UpdateUserFieldUseCase(repository)
        )
    )

    // 👇 Start observing user once UID is available
    LaunchedEffect(uid) {
        uid?.let { balanceViewModel.startObservingUser(it) }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Top Up",
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
//            BalanceUI(fontFamily)
//            Spacer(modifier = Modifier.height(20.dp))
//            QuickAction(fontFamily, balanceViewModel, uid)
//            Spacer(modifier = Modifier.height(20.dp))
            ShopUI(fontFamily, balanceViewModel, uid)
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

val topUpItems = listOf(
    TopUp(
        50.00
    ),
    TopUp(
        100.00
    ),
    TopUp(
        200.00
    ),
    TopUp(
        300.00
    ),
    TopUp(
        400.00
    ),
    TopUp(
        500.00
    ),
)


@Composable
fun ShopUI(
    fontFamily: FontFamily,
    balanceViewModel: BalanceViewModel,
    uid: String?
) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(topUpItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(10.dp)),
                    colors = CardDefaults.cardColors(containerColor = darkPageBackground),
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(75.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.coin_3d),
                                contentDescription = "Icon Logo",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()

                            )
                        }


                        Row (
                            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "PHP",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.LightGray,
                                fontFamily = fontFamily
                            )

                            Text(
                                text = "${item.amound}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.LightGray,
                                fontFamily = fontFamily

                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(top = 20.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color(0xFF2F2FFD))
                                .clickable {
                                    uid?.let { balanceViewModel.updateUserBalance(it, item.amound) }
                                },
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "Buy",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontFamily
                            )
                        }
                    }
                }
            }
        }
    }