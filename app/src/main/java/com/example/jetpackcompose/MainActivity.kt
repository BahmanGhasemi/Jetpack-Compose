package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.compose.JetpackComposeTheme

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
                    )
                    {

                    }
                }
            }
        }
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    isOutline: Boolean,
    onClick: () -> Unit
) {
    when (isOutline) {
        true -> OutlinedButton(onClick = onClick) {
            Text(text = title)
        }

        false -> Button(onClick = onClick) {
            Text(text = title)
        }
    }
}


@Preview(
//    widthDp = 300,
//    heightDp = 300
//    device = Devices.TABLET,
//    showSystemUi = true,
//    locale = "fa",
//    fontScale = 2f,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
)
//@PreviewFontScale
@PreviewDynamicColors
@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun CustomButtonPreview(
    @PreviewParameter(OutlinePreviewParameter::class) isOutline: Boolean
) {
    JetpackComposeTheme {
        val insMode = LocalInspectionMode.current
        CustomButton(
            modifier = Modifier.clip(if (insMode) CutCornerShape(8.dp) else ButtonDefaults.shape),
            title = "Test Button",
            isOutline = isOutline,
            onClick = {}
        )
    }
}

class OutlinePreviewParameter : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)

}