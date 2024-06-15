package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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
                    )
                    {
                        val navController = rememberNavController()
                        AppNavigation(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "auth") {
        composable("about"){

        }
        navigation(startDestination = "login", route = "auth") {
            composable("login") {
                val viewmodel = it.shareViewModel<SampleViewModel>(navController = navController)
                println(viewmodel)
                LoginScreen(navController = navController)
            }
            composable("register") {
                val viewmodel = it.shareViewModel<SampleViewModel>(navController = navController)
                println(viewmodel)
                RegisterScreen(navController = navController)
            }
            composable("forgot_password") {
                val viewmodel = it.shareViewModel<SampleViewModel>(navController = navController)
                println(viewmodel)
                ForgotPasswordScreen(navController = navController)
            }
        }
        navigation(
            startDestination = "calendar_overview",
            route = "calendar"
        ) {
            composable("calendar_overview") {
            }

            composable("calendar_") {
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.shareViewModel(navController: NavController):T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}