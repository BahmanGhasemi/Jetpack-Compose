package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                var selectedIndex by remember { mutableIntStateOf(0) }
                val currentScreenWidth =
                    currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    {
                        NavigationSuiteScaffold(
                            navigationSuiteItems = {
                                NavItem.getItems().forEachIndexed { index, navItem ->
                                    item(
                                        selected = (index == selectedIndex),
                                        label = { Text(text = navItem.title) },
                                        icon = {
                                            Icon(
                                                imageVector = if (index == selectedIndex) navItem.selectedIcon else navItem.unselectedIcon,
                                                contentDescription = null
                                            )
                                        },
                                        onClick = { selectedIndex = index }
                                    )
                                }
                            },
                            layoutType = when (currentScreenWidth) {
                                WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationDrawer
                                WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationRail
                                else -> NavigationSuiteType.NavigationBar
                            }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                NavItem.getItems().forEachIndexed { index, navItem ->
                                    if (index == selectedIndex)
                                        Text(text = NavItem.getItems()[index].title)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    companion object {
        fun getItems(): List<NavItem> = listOf(
            NavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
            NavItem("Search", Icons.Filled.Search, Icons.Outlined.Search),
            NavItem("Setting", Icons.Filled.Settings, Icons.Outlined.Settings)
        )
    }
}