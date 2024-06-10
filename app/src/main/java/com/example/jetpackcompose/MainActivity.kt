package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val state = rememberDrawerState(initialValue = DrawerValue.Closed)
                var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(modifier = Modifier.padding(top = 16.dp)) {
                            val items = NavItem.getItems()
                            items.forEachIndexed { index, navItem ->
                                NavigationDrawerItem(
                                    modifier = Modifier.padding(8.dp),
                                    label = { Text(text = navItem.title) },
                                    selected = (index == selectedIndex),
                                    onClick = {
                                        selectedIndex = index
                                        scope.launch { state.close() }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedIndex) navItem.selectedIcon else navItem.unSelectedIcon,
                                            contentDescription = null
                                        )
                                    },
                                    badge = {
                                        navItem.badgeCount?.let {
                                            Text(text = it.toString())
                                        }
                                    }
                                )
                            }
                        }
                    },
                    drawerState = state
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "Todo App") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch { state.open() }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )
                        }) { innerPadding ->
                        LazyColumn(modifier = Modifier.padding(innerPadding)) {
                            items(100) {
                                Text(text = "Item $it")
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
    val unSelectedIcon: ImageVector,
    val badgeCount: Int? = null
) {
    companion object {
        fun getItems() = listOf(
            NavItem(
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unSelectedIcon = Icons.Outlined.Person
            ),

            NavItem(
                title = "Unread Messages",
                selectedIcon = Icons.Filled.Email,
                unSelectedIcon = Icons.Outlined.Email,
                badgeCount = 65
            ),

            NavItem(
                title = "Setting",
                selectedIcon = Icons.Filled.Settings,
                unSelectedIcon = Icons.Outlined.Settings
            )
        )
    }
}