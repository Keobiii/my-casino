package com.example.casino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.casino.data.repository.UserRepositoryImpl
import com.example.casino.domain.usecase.ObserveUserDataUseCase
import com.example.casino.domain.usecase.TransactionHistoryUseCase
import com.example.casino.domain.usecase.UpdateUserFieldUseCase
import com.example.casino.presentation.ui.Home.GameUI
import com.example.casino.presentation.ui.Home.Home
import com.example.casino.utils.TopNavigationBar
import com.example.casino.presentation.ui.Profile.Profile
import com.example.casino.presentation.ui.Shop.Shop
import com.example.casino.presentation.ui.Shop.TopUp
import com.example.casino.presentation.viewmodel.BalanceViewModel
import com.example.casino.presentation.viewmodel.BalanceViewModelFactory
import com.example.casino.ui.theme.CasinoTheme
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.DataStoreManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CasinoTheme {
                SetBarColor(color = pageBackground)

                var isSplashFinished by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = pageBackground
                ) {
//                    if (isSplashFinished) {
//                        Navigation()
//                    } else {
//                        SplashScreen {
//                            isSplashFinished = true
//                        }
//                    }

                    Navigation()
                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}


@Composable
fun Navigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val dataStoreManager = remember { DataStoreManager(context) }

    // Get UID from DataStore
    val uid by dataStoreManager.getUserUid().collectAsState(initial = null)

    // Initialize ViewModel
    val repository = UserRepositoryImpl()
    val updateUserFieldUseCase = UpdateUserFieldUseCase(repository)
    val balanceViewModel: BalanceViewModel = viewModel(
        factory = BalanceViewModelFactory(
            observeUserDataUseCase = ObserveUserDataUseCase(repository),
            updateUserFieldUseCase = UpdateUserFieldUseCase(repository),
            transactionHistoryUseCase = TransactionHistoryUseCase(updateUserFieldUseCase)
        )
    )

    // Start observing user data from Firebase
    LaunchedEffect(uid) {
        uid?.let { balanceViewModel.startObservingUser(it) }
    }

    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
    )

    Scaffold(
        topBar = {
            TopNavigationBar(
                navController = navController,
                fontFamily = fontFamily,
                balanceViewModel = balanceViewModel
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { Home(navController, fontFamily) }
            composable("top_up") { Shop(fontFamily) }
            composable("profile") { Profile(fontFamily) }
            composable(
                "gameUI/{index}/{title}",
                arguments = listOf(
                    navArgument("index") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index") ?: 0
                val title = backStackEntry.arguments?.getString("title") ?: "Unknown"
                GameUI(index, fontFamily, title)
            }
        }
    }
}


@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
    )

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coin))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 9999,
        isPlaying = true,
        speed = 1f
    )

    LaunchedEffect(Unit) {
        delay(3000)
        onSplashFinished()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.size(180.dp)
        )
//
//        Text(
//            text = "Win Or Cry",
//            fontFamily = fontFamily,
//            fontSize = 30.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            color = eggWhite
//        )

//        Box(
//            modifier = Modifier.fillMaxWidth().height(170.dp).border(1.dp, Color.Red).padding(20.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Image(
//                modifier = Modifier.fillMaxSize().border(1.dp, Color.Blue),
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "logo",
//                contentScale = ContentScale.FillWidth
//            )
//        }
    }
}

