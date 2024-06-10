package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
                        RadioButtons()
                    }
                }
            }
        }
    }
}

data class ObjectInfo(
    val isSelected: Boolean,
    val text: String
)

@Composable
private fun RadioButtons() {
    val items = remember {
        mutableStateListOf(
            ObjectInfo(true, "Option 1"),
            ObjectInfo(false, "Option 2"),
            ObjectInfo(false, "Option 3")
        )
    }

    items.forEachIndexed { index, info ->
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = info.isSelected,
                onClick = {
                    items.replaceAll {
                        it.copy(isSelected = it.text == info.text)
                    }
                })
            Text(text = info.text)
        }
    }
}