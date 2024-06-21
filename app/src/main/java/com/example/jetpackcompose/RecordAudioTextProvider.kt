package com.example.jetpackcompose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

class RecordAudioTextProvider : PermissionTextProvider {
    override fun getDescription(isDeclined: Boolean): String {
        return if (isDeclined) {
            buildAnnotatedString {
                append("It seems you permanently declined ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                ) {
                    append("Record Audio")
                }
                append(
                    " permission. " +
                            "so you can go to setting to grant it."
                )
            }.toString()
        } else {
            "It's need for your friend hear you during a phone call."
        }
    }
}