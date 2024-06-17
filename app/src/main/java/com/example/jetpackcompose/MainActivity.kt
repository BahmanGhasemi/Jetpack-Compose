package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "Screen1") {
                            composable("Screen1") { entry ->
                                val text = entry.savedStateHandle.get<String>("my_key")
                                Column(Modifier.fillMaxSize()) {
                                    text?.let {
                                        Text(text = it)
                                    }
                                    Button(onClick = {
                                        navController.navigate("Screen2")
                                    }) {
                                        Text(text = "Go to Screen2")
                                    }
                                }
                            }
                            composable("Screen2") {
                                Column(Modifier.fillMaxSize()) {
                                    var text by remember { mutableStateOf("") }
                                    TextField(value = text, onValueChange = { text = it })
                                    Button(onClick = {
                                        navController.previousBackStackEntry?.savedStateHandle?.set(
                                            "my_key",
                                            text
                                        )
                                        navController.popBackStack()
                                    }) {
                                        Text(text = "Go Back")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}