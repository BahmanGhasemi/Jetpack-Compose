package com.example.jetpackcompose._03_stateful_dependency

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun StateFulDependency() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            val viewModel = hiltViewModel<Screen1ViewModel>()
            val count = viewModel.count.collectAsStateWithLifecycle()
            Button(onClick = {
                viewModel.inc()
                navController.navigate("Detail")
            }) {
                Text(text = "screen1 counter: ${count.value}")
            }
        }
        composable("Detail") {
            val viewModel = hiltViewModel<Screen2ViewModel>()
            val count = viewModel.count.collectAsStateWithLifecycle()
            Text(text = "screen2 counter: ${count.value}")
        }
    }
}