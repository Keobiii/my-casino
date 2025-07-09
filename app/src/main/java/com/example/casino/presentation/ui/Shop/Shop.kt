package com.example.casino.presentation.ui.Shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.casino.ui.theme.pageBackground
import com.example.casino.utils.Shop_TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Shop(
    fontFamily: FontFamily
) {
    val tabItems = listOf(
        Shop_TabItem("Top Up", page = {
            PayScreen()
        }),
        Shop_TabItem("Withdraw", page = {
            Withdraw()
        }),
        Shop_TabItem("History", page = {
            History(fontFamily)
        })
    )




    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {
        var selectedTabIndex by remember { mutableStateOf(0) }
        val pagerState = rememberPagerState(
            tabItems.size
        )
        // trigger when selectabIndex changes
//        LaunchedEffect(selectedTabIndex) {
//            pagerState.animateScrollToPage(selectedTabIndex)
//        }
//        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
//            if (!pagerState.isScrollInProgress) {
//                selectedTabIndex = pagerState.currentPage
//            }
//        }

        val scope = rememberCoroutineScope()
        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex
            ) {
                tabItems.forEachIndexed{ index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
//                            selectedTabIndex = index
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }

                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = item.title,
                                fontFamily = fontFamily,
                                color = Color.White
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->

                tabItems[index].page()
            }

        }
    }
}