package com.example.jetpackcompose._02_shared_view_model

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun SharedViewModelTest() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "login",
            route = "auth"
        ) {
            composable("login") {
                val viewmodel = it.sharedViewModel<CustomViewModel>(navController = navController)
                val count = viewmodel.count.collectAsStateWithLifecycle()
                Button(onClick = {
                    viewmodel.inc()
                    navController.navigate("register")
                }) {
                    Text(text = "counter in screen1: ${count.value}")
                }
            }
            composable("register") {
                val viewmodel = it.sharedViewModel<CustomViewModel>(navController = navController)
                val count = viewmodel.count.collectAsStateWithLifecycle()
                Text(text = "counter in screen2: ${count.value}")
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val graphRoute = destination.parent?.route ?: return viewModel()
    val parent = remember(this) {
        navController.getBackStackEntry(graphRoute)
    }
    return viewModel(parent)
}