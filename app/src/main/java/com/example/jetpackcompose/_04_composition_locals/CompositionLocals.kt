package com.example.jetpackcompose._04_composition_locals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@Composable
fun CompositionLocalsTest() {
    val state = LocalSnackState.current
    CompositionLocalProvider(LocalSnackState provides state) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = LocalSnackState.current) }
        ) { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                MyScreen()
            }
        }
    }
}

@Composable
fun MyScreen() {
    val state = LocalSnackState.current
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            state.showSnackbar("Showing from Composition Local State")
        }
    }) {
        Text(text = "Show Snack")
    }
}


val LocalSnackState = compositionLocalOf {
    SnackbarHostState()
}