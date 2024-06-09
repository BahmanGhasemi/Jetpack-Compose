package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.screen.FeedScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.screen.ProfileScreen
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                DrawerNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerNavigation() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(1f)
                    .background(Color.White),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.Top)
            ) {

                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                currentRoute?.let { route ->
                    NavigationDrawerItem(
                        label = { Text(text = Destination.Home.route) },
                        selected = (route == Destination.Home.route),
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Destination.Home.route)
                        })

                    NavigationDrawerItem(
                        label = { Text(text = Destination.Feed.route) },
                        selected = (route == Destination.Feed.route),
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Destination.Feed.route)
                        })

                    NavigationDrawerItem(
                        label = { Text(text = Destination.Profile.route) },
                        selected = (route == Destination.Profile.route),
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(Destination.Profile.route)
                        })
                }
            }
        }
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = "Navigation Drawer") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (drawerState.isOpen)
                            scope.launch { drawerState.close() }
                        else
                            scope.launch { drawerState.open() }
                    }) {
                        Icon(Icons.Rounded.Menu, contentDescription = null)
                    }
                }
            )
        }) {

            NavHost(navController = navController, startDestination = Destination.Home.route) {
                composable(Destination.Home.route) { HomeScreen() }
                composable(Destination.Feed.route) { FeedScreen() }
                composable(Destination.Profile.route) { ProfileScreen() }
            }
        }
    }
}