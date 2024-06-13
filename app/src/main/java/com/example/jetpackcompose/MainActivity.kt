package com.example.jetpackcompose

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<SearchViewModel>()
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val isLoading by viewModel.isLoading.collectAsState()
                    val searchText by viewModel.searchText.collectAsState()
                    val persons by viewModel.persons.collectAsState()

                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    )
                    {
                        TextField(
                            value = searchText, onValueChange = viewModel::onSearchTextChange,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            LazyColumn(Modifier.fillMaxSize(1f)) {
                                items(persons) {
                                    Text(
                                        text = "${it.firstName} ${it.lastName}",
                                        modifier = Modifier.padding(vertical = 16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}