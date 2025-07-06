package com.example.casino.presentation.ui.Shop

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.casino.R
import com.example.casino.data.model.TopUp
import com.example.casino.data.repository.UserRepositoryImpl
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.TransactionHistoryUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.presentation.viewmodel.BalanceViewModel
import com.example.casino.presentation.viewmodel.BalanceViewModelFactory
import com.example.casino.ui.theme.darkPageBackground
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.DataStoreManager
import com.example.casino.utils.UiState
import kotlinx.coroutines.delay


@Composable
fun TopUp(
    fontFamily: FontFamily
) {
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val uid by dataStoreManager.getUserUid().collectAsState(initial = null)

    val repository = UserRepositoryImpl()
    val updateUserFieldUseCase = UpdateUserFieldUseCase(repository)
    val balanceViewModel: BalanceViewModel = viewModel(
        factory = BalanceViewModelFactory(
            observeUserDataUseCase = ObserveUserDataUseCase(repository),
            updateUserFieldUseCase = UpdateUserFieldUseCase(repository),
            transactionHistoryUseCase = TransactionHistoryUseCase(updateUserFieldUseCase)
        )
    )

    LaunchedEffect(uid) {
        uid?.let { balanceViewModel.startObservingUser(it) }
    }

    val updateState = balanceViewModel.updateBalanceState


    LaunchedEffect(updateState) {
        if (updateState == UiState.Idle) return@LaunchedEffect

        when (updateState) {
            is UiState.Success -> {
                Toast.makeText(context, "Balance updated!", Toast.LENGTH_SHORT).show()
            }
            is UiState.Error -> {
                Toast.makeText(context, updateState.message, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }

        delay(1000)
        balanceViewModel.resetUpdateBalanceState()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(pageBackground)
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
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

                ShopUI(fontFamily, balanceViewModel, uid)
            }


            if (updateState is UiState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
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
        -200.00
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