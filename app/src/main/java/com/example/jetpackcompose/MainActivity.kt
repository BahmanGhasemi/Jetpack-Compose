package com.example.jetpackcompose

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.ui.window.Dialog


@OptIn(ExperimentalMaterial3Api::class)
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
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(
                            32.dp,
                            alignment = Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        val items = listOf("Date Picker", "Range Picker", "Time Picker")
                        val dateState = rememberDatePickerState()
                        val rangeState = rememberDateRangePickerState()
                        val timeState = rememberTimePickerState()
                        var showDialog by remember { mutableStateOf(false) }
                        var selectedIndex by remember { mutableIntStateOf(0) }

                        ModeSelection(items, selectedIndex) { selectedIndex = it }
                        Button(onClick = { showDialog = true }) {
                            Text(text = "Open Dialog")
                        }
                        Text(
                            text = when (selectedIndex) {
                                0 -> "${dateState.selectedDateMillis}"
                                1 -> "${rangeState.selectedStartDateMillis} to ${rangeState.selectedEndDateMillis}"
                                2 -> "${timeState.hour}:${timeState.minute}"
                                else -> "Undefined !"
                            }
                        )

                        if (showDialog) {
                            when (val index = selectedIndex) {
                                0, 1 -> {
                                    DatePickerDialog(
                                        onDismissRequest = { showDialog = false },
                                        confirmButton = {
                                            Button(onClick = { showDialog = false }) {
                                                Text(text = "Ok")
                                            }
                                        })
                                    {
                                        when (index) {
                                            0 -> {
                                                DatePicker(state = dateState)
                                            }

                                            1 -> {
                                                DateRangePicker(state = rangeState)
                                            }
                                        }
                                    }
                                }

                                2 -> {
                                    AlertDialog(
                                        onDismissRequest = { showDialog = false },
                                        confirmButton = {
                                            Button(onClick = { showDialog = false }) {
                                                Text(text = "ok")
                                            }
                                        },
                                        text = {
                                            TimePicker(state = timeState)
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ModeSelection(
    items: List<String>,
    selectedIndex: Int,
    onClick: (index: Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.Top)
    ) {
        items.forEachIndexed { index, title ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = (selectedIndex == index), onClick = { onClick(index) })
                Text(text = title)
            }
        }
    }
}