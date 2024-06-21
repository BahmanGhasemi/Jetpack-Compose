package com.example.jetpackcompose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

class CameraTextProvider : PermissionTextProvider {
    override fun getDescription(isDeclined: Boolean): String {
        return if (isDeclined) {
            buildAnnotatedString {
                append("It seems you permanently declined ")
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )){
                    append("Camera")
                }
                append(" permission. " +
                        "so you can go to setting to grant it.")
            }.toString()

        } else
            "This permission required for using camera during calling with friend, they can see you."
    }
}