package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val windowsSize = calculateWindowSizeClass(activity = this)
                val showRailNavigation = windowsSize.widthSizeClass != WindowWidthSizeClass.Compact
                var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                val onNavigateCallBack: (index: Int) -> Unit = {
                    selectedIndex = it
                }


                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (!showRailNavigation)
                            DrawBottomNavigation(
                                items = NavItem.getItems(),
                                selectedIndex = selectedIndex,
                                onNavigate = onNavigateCallBack
                            )
                    }
                ) { innerPadding ->
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(start = if (showRailNavigation) 80.dp else 0.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top)
                    ) {
                        items(100) {
                            Text(text = "Item $it", modifier = Modifier.padding(16.dp))
                        }
                    }
                }

                if (showRailNavigation) {
                    DrawRailNavigation(
                        items = NavItem.getItems(),
                        selectedIndex,
                        onNavigate = onNavigateCallBack
                    )
                }
            }
        }
    }

}

@Composable
private fun DrawRailNavigation(
    items: List<NavItem>,
    selectedIndex: Int,
    onNavigate: (index: Int) -> Unit
) {
    NavigationRail(
        header = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items
                .forEachIndexed { index, navItem ->
                    NavigationRailItem(
                        selected = (index == selectedIndex),
                        onClick = { onNavigate(index) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    when {
                                        navItem.hasNews -> {
                                            Badge()
                                        }

                                        navItem.badgeCount != null -> {
                                            Badge {
                                                Text(text = navItem.badgeCount.toString())
                                            }
                                        }
                                    }
                                }) {
                                Icon(
                                    imageVector = if (index == selectedIndex) navItem.selectedIcon else navItem.unSelectedIcon,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
        }
    }
}

@Composable
private fun DrawBottomNavigation(
    items: List<NavItem>,
    selectedIndex: Int,
    onNavigate: (index: Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = (index == selectedIndex),
                onClick = { onNavigate(index) },
                icon = {
                    BadgedBox(badge = {
                        when {
                            navItem.hasNews -> Badge()
                            navItem.badgeCount != null -> Badge {
                                Text(text = navItem.badgeCount.toString())
                            }
                        }
                    }) {
                        Icon(
                            imageVector = if (index == selectedIndex) navItem.selectedIcon else navItem.unSelectedIcon,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
) {
    companion object {
        fun getItems() = listOf(
            NavItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home,
                false
            ),

            NavItem(
                title = "Chat's",
                selectedIcon = Icons.Filled.Email,
                unSelectedIcon = Icons.Outlined.Email,
                false,
                badgeCount = 7
            ),

            NavItem(
                title = "Notification",
                selectedIcon = Icons.Filled.Notifications,
                unSelectedIcon = Icons.Outlined.Notifications,
                true
            )
        )
    }
}