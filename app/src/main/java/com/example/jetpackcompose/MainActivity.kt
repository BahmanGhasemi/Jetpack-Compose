package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.serialization.Serializable

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
                    )
                    {
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = ScreenA) {
                            composable<ScreenA> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(onClick = {
                                        navController.navigate(
                                            ScreenB(
                                                name = null,
                                                age = 32
                                            )
                                        )
                                    }) {
                                        Text(text = "Go to Next")
                                    }
                                }
                            }

                            composable<ScreenB> { entry ->
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val args = entry.toRoute<ScreenB>()
                                    Text(text = "${args.name}, ${args.age} years old.")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)