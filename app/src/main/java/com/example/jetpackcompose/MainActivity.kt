package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose._01_simple_transfer_data.SimpleNavArgument
import com.example.jetpackcompose._02_shared_view_model.SharedViewModelTest
import com.example.jetpackcompose._03_stateful_dependency.StateFulDependency
import com.example.jetpackcompose._04_composition_locals.CompositionLocalsTest
import com.example.jetpackcompose._05_persistant_storage.PersistentStorageTest
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
//                        SimpleNavArgument()
//                        SharedViewModelTest()
//                        StateFulDependency()
//                        CompositionLocalsTest()
                        PersistentStorageTest()
                    }
                }
            }
        }
    }
}