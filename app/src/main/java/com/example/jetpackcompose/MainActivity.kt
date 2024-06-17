package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.screen.Destination
import com.example.jetpackcompose.screen.DetailScreen
import com.example.jetpackcompose.screen.ListScreen
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }

    @Composable
    private fun AppNavigation(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Destination.List.route) {
            composable(Destination.List.route) { ListScreen(navController = navController) }
            composable(Destination.Detail.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("userId")
                id?.let {
                    DetailScreen(navController = navController, id = it.toInt())
                }
            }
        }
    }
}