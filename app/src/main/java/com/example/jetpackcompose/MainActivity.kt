package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val rootNavController = rememberNavController()
                val backStackEntry by rootNavController.currentBackStackEntryAsState()
                var selectedItemIndex by remember { mutableIntStateOf(0) }

                val items = NavItem.getItems()
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    NavigationBar {
                        items.forEachIndexed { index, navItem ->
                            val isSelected = (navItem.title == backStackEntry?.destination?.route)
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    rootNavController.navigate(navItem.title) {
                                        popUpTo(rootNavController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (isSelected) navItem.selectedIcon else navItem.unSelectedIcon,
                                        contentDescription = null
                                    )
                                },
                                label = { Text(text = navItem.title) })
                        }
                    }

                }) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    {
                        NavHost(rootNavController, startDestination = "Home"){
                            composable("Home"){ NavHome()}
                            composable("Chats"){ NavChat() }
                            composable("Settings"){ NavSetting() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavHome() {
    val navHome = rememberNavController()
    NavHost(navController = navHome, startDestination = "Home1") {
        for (i in 1..10) {
            composable("Home$i") {
                CreateScreen(text = "Home$i") {
                    if (i < 10)
                        navHome.navigate("Home${i + 1}")
                }
            }
        }
    }
}

@Composable
fun NavChat() {
    val navChat = rememberNavController()
    NavHost(navController = navChat, startDestination = "Chat1") {
        for (i in 1..10) {
            composable("Chat$i") {
                CreateScreen(text = "Chat$i") {
                    if (i < 10)
                        navChat.navigate("Chat${i + 1}")
                }
            }
        }
    }
}

@Composable
fun NavSetting() {
    val navSetting = rememberNavController()
    NavHost(navController = navSetting, startDestination = "Setting1") {
        for (i in 1..10) {
            composable("Setting$i") {
                CreateScreen(text = "Setting$i") {
                    if (i < 10)
                        navSetting.navigate("Setting${i + 1}")
                }
            }
        }
    }
}

@Composable
fun CreateScreen(text: String, onNextClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(text = text)
        Button(onClick = onNextClick) {
            Text(text = "Next")
        }
    }
}

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    companion object {
        fun getItems() = listOf(
            NavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
            NavItem("Chats", Icons.Filled.Send, Icons.Outlined.Send),
            NavItem("Settings", Icons.Filled.Settings, Icons.Outlined.Settings)
        )
    }
}