//package com.example.casino.presentation.ui.Shop
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Tab
//import androidx.compose.material3.TabRow
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.casino.theme.CasinoTheme
//import com.example.casino.ui.theme.pageBackground
//import com.example.casino.utils.Shop_TabItem
//
//class Shop : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        val tabItems = listOf(
//            Shop_TabItem(
//                title = "Shop"
//            ),
//            Shop_TabItem(
//                title = "Withdraw"
//            ),
//            Shop_TabItem(
//                title = "History"
//            )
//        )
//        setContent {
//            CasinoTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = pageBackground
//                ) {
//                    var selectedTabIndex by remember { mutableStateOf(0) }
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                    ) {
//                        TabRow(
//                            selectedTabIndex = selectedTabIndex
//                        ) {
//                            tabItems.forEachIndexed{ index, item ->
//                                Tab(
//                                    selected = index == selectedTabIndex,
//                                    onClick = {
//                                        selectedTabIndex = index
//                                    },
//                                    text = {
//                                        Text(
//                                            text = item.title,
//                                            fontFamily = FontFamily.SansSerif
//                                        )
//                                    }
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}