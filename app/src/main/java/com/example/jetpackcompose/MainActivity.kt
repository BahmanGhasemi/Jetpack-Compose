package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val items = remember {
                    mutableStateListOf(
                        NavItem(
                            title = "Home",
                            selectedIcon = Icons.Filled.Home,
                            unSelectedIcon = Icons.Outlined.Home,
                            hasNews = false
                        ),

                        NavItem(
                            title = "Chats",
                            selectedIcon = Icons.Filled.Email,
                            unSelectedIcon = Icons.Outlined.Email,
                            hasNews = false,
                            badgeCount = 47
                        ),

                        NavItem(
                            title = "Notification",
                            selectedIcon = Icons.Filled.Notifications,
                            unSelectedIcon = Icons.Outlined.Notifications,
                            hasNews = true
                        )
                    )
                }

                var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            items.forEachIndexed { index, navItem ->
                                NavigationBarItem(
                                    selected = (index == selectedIndex),
                                    onClick = {
                                        selectedIndex = index
                                        when {
                                            navItem.hasNews -> items[index] =
                                                navItem.copy(hasNews = false)

                                            navItem.badgeCount != null -> items[index] =
                                                navItem.copy(badgeCount = null)
                                        }
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                when {
                                                    navItem.hasNews -> Badge()
                                                    navItem.badgeCount != null -> Badge {
                                                        Text(text = "${if (navItem.badgeCount > 99) "+99" else navItem.badgeCount}")
                                                    }
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (selectedIndex == index) navItem.selectedIcon else navItem.unSelectedIcon,
                                                contentDescription = null
                                            )
                                        }
                                    },
                                    alwaysShowLabel = false,
                                    label = { Text(text = navItem.title) }
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                }
            }
        }
    }
}

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)