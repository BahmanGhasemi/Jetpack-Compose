package com.example.jetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.screen.FeedScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.screen.ProfileScreen
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val navController = rememberNavController()
                AppBottomNavigation(navController = navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun AppBottomNavigation(navController: NavHostController) {
    Scaffold(bottomBar = {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        currentRoute?.let { route ->
            BottomNavigation {
                BottomNavigationItem(
                    selected = (route == Destination.Home.route),
                    onClick = { navController.navigate(Destination.Home.route) },
                    icon = { Icon(Icons.Rounded.Home, contentDescription = null) },
                    label = { Text(Destination.Home.route) })

                BottomNavigationItem(
                    selected = (route == Destination.Feed.route),
                    onClick = {
                        navController.navigate(Destination.Feed.route) {
                            popUpTo(Destination.Home.route)
                        }
                    },
                    icon = { Icon(Icons.AutoMirrored.Rounded.List, contentDescription = null) },
                    label = { Text(Destination.Feed.route) })

                BottomNavigationItem(
                    selected = (route == Destination.Profile.route),
                    onClick = {
                        navController.navigate(Destination.Profile.route) {
                            popUpTo(Destination.Home.route)
                        }
                    },
                    icon = { Icon(Icons.Rounded.Person, contentDescription = null) },
                    label = { Text(Destination.Profile.route) })
            }
        }

    }) {
        NavHost(navController = navController, startDestination = Destination.Home.route) {
            composable(Destination.Home.route) { HomeScreen() }
            composable(Destination.Feed.route) { FeedScreen() }
            composable(Destination.Profile.route) { ProfileScreen() }
        }
    }
}
