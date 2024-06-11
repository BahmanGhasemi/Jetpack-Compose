package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fonts = FontFamily(
            listOf(
                Font(R.font.dancing_script_regular, weight = FontWeight.Normal),
                Font(R.font.dancing_script_medium, weight = FontWeight.Medium),
                Font(R.font.dancing_script_bold, weight = FontWeight.Bold)
            )
        )


        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Blue,
                                        fontSize = 48.sp,
                                        fontFamily = fonts
                                    )
                                ) {
                                    append("H")
                                }
                                append("ello ")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Green,
                                        fontSize = 48.sp,
                                        fontFamily = fonts
                                    )
                                ) {
                                    append("W")
                                }
                                append("orld")
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Red,
                                    )
                                ) {
                                    append("!")
                                }
                            },
                            color = Color(0xff101010),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                        )
                    }
                }
            }
        }
    }
}