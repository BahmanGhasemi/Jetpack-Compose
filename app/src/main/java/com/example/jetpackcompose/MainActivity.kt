package com.example.jetpackcompose

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                val date by remember { mutableStateOf(LocalDate.of(2024, 6, 12)) }
                val time by remember { mutableStateOf(LocalTime.parse("09:17")) }
                val dateTime by remember { mutableStateOf(LocalDateTime.now()) }
                val zonedDateTime by remember { mutableStateOf(ZonedDateTime.ofInstant(
                    Instant.ofEpochMilli(System.currentTimeMillis()),
                    ZoneId.of("Asia/Tokyo")
                )) }
                val formatter by remember {
                    mutableStateOf(
                        DateTimeFormatter
                            .ofPattern("EEE dd-MM-yyyy HH:mm")
                            .format(zonedDateTime)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Text(text = date.toString())
                    Text(text = time.toString())
                    Text(text = dateTime.toString())
                    Text(text = formatter.toString())
                }
            }
        }
    }
}