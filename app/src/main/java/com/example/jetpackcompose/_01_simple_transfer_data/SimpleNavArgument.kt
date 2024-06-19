package com.example.jetpackcompose._01_simple_transfer_data

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SimpleNavArgument() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            var text by remember { mutableStateOf("") }

            Column {
                TextField(value = text, onValueChange = { text = it })
                Button(onClick = {
                    navController.navigate("Detail/$text")
                }) {
                    Text(text = "go to next")
                }
            }
        }
        composable(
            "Detail/{my_arg}",
            arguments = listOf(
                navArgument(name = "my_arg") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val text = entry.arguments?.getString("my_arg") ?: "NO_DATA"
            Text(text = text)
        }
    }
}