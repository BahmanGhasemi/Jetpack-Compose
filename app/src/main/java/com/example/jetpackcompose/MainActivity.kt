package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    )
                    {
                        Switches()
                    }
                }
            }
        }
    }
}

data class ToggleInfo(
    val isChecked: Boolean,
    val text: String
)

@Composable
fun Switches() {
    var info by remember { mutableStateOf(ToggleInfo(false, "dark mode")) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = info.text)
        Spacer(modifier = Modifier.weight(1f))
        Switch(checked = info.isChecked, onCheckedChange = {
            info = info.copy(isChecked = it)
        },
            thumbContent = {
                Icon(
                    imageVector = if (info.isChecked) Icons.Rounded.Check else Icons.Rounded.Close,
                    contentDescription = null
                )
            })
    }
}