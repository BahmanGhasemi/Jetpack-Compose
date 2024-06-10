package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Scaffold {
                    val items = TabItem.getItems()
                    var selectedTabIndex by remember { mutableIntStateOf(0) }
                    val pagerState = rememberPagerState { items.size }
                    LaunchedEffect(key1 = selectedTabIndex) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }
                    LaunchedEffect(key1 = pagerState, key2 = pagerState.isScrollInProgress) {
                        if (!pagerState.isScrollInProgress)
                            selectedTabIndex = pagerState.currentPage
                    }

                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        TabRow(selectedTabIndex = selectedTabIndex) {
                            items.forEachIndexed { index, tabItem ->
                                Tab(
                                    selected = (index == selectedTabIndex),
                                    onClick = { selectedTabIndex = index },
                                    text = { Text(text = tabItem.title) },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedTabIndex) tabItem.selectedIcon else tabItem.unSelectedIcon,
                                            contentDescription = null
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
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(text = items[pagerState.currentPage].title)
                            }
                        }
                    }
                }
            }
        }
    }
}

data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    companion object {
        fun getItems() = listOf(
            TabItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home
            ),
            TabItem(
                title = "Browse",
                selectedIcon = Icons.Filled.ShoppingCart,
                unSelectedIcon = Icons.Outlined.ShoppingCart
            ),
            TabItem(
                title = "Account",
                selectedIcon = Icons.Filled.AccountCircle,
                unSelectedIcon = Icons.Outlined.AccountCircle
            ),
        )
    }
}