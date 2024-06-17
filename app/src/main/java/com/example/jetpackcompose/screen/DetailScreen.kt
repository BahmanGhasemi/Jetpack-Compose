package com.example.jetpackcompose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.jetpackcompose.User

@Composable
fun DetailScreen(navController: NavController, id: Int) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = User.getUser(id).toString())
        Button(onClick = {
            if (navController.canNavigateBack())
                navController.popBackStack()
        }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            Text(text = "Back")
        }
    }
}

fun NavController.canNavigateBack() =
    currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED