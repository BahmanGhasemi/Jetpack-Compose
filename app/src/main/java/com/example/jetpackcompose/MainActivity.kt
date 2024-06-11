package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<MyViewModel>()
            val isLoading = viewModel.isLoading.asStateFlow().collectAsState()
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading.value)
            JetpackComposeTheme {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = { viewModel.loadingStuff() }
                ) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(100) {
                            Text(text = "Item $it",
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp))
                        }
                    }
                }
            }
        }
    }
}