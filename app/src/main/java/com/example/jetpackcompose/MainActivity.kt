package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
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
                    ) {
                        CheckBoxes()
                    }
                }
            }
        }
    }
}

data class CheckBoxInfo(
    val isChecked: Boolean,
    val text: String
)

@Composable
fun CheckBoxes() {
    val items = remember {
        mutableStateListOf(
            CheckBoxInfo(false, "Photo"),
            CheckBoxInfo(false, "Video"),
            CheckBoxInfo(false, "Audio")
        )
    }

    var triState by remember { mutableStateOf(ToggleableState.Indeterminate) }
    val triStateClick = {
        triState = when (triState) {
            ToggleableState.Indeterminate, ToggleableState.Off -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
        }
        items.indices.forEach {
            items[it] = items[it].copy(isChecked = triState == ToggleableState.On)
        }
    }


    Row(
        modifier = Modifier
            .clickable { triStateClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        TriStateCheckbox(state = triState, onClick = triStateClick)
        Text(text = "File Type")
    }

    items.forEachIndexed { index, info ->
        Row(modifier = Modifier
            .padding(start = 32.dp)
            .clickable { items[index] = items[index].copy(isChecked = !info.isChecked) }
            .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = info.isChecked,
                onCheckedChange = { items[index] = items[index].copy(isChecked = !info.isChecked) })
            Text(text = info.text)
        }
    }

}