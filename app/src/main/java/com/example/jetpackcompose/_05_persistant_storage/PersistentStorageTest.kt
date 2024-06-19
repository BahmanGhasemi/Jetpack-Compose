package com.example.jetpackcompose._05_persistant_storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PersistentStorageTest() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Screen1") {
        composable("Screen1") {
            val viewmodel = hiltViewModel<Screen1ViewModel>()

            LaunchedEffect(Unit) {
                println("screen1 token: ${viewmodel.session}")
            }

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Button(onClick = {
                    viewmodel.saveSession()
                    navController.navigate("Screen2")
                }) {
                    Text(text = "token: ${viewmodel.session}")
                }

                Button(onClick = { viewmodel.reset() }) {
                    Text(text = "Reset Token")
                }
            }

        }

        composable("Screen2") {
            val viewModel = hiltViewModel<Screen2ViewModel>()
            LaunchedEffect(Unit) {
                println("screen2 token: ${viewModel.session}")
            }
            Text(text = "screen2 token: ${viewModel.session}")
        }
    }
}