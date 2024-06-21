package com.example.jetpackcompose

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<PermissionHandlerViewModel>()
            val permissionQueue = viewModel.permissions

            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val singlePermissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { isGranted ->
                            viewModel.onPermissionResult(
                                permission = Manifest.permission.CAMERA,
                                isGranted = isGranted
                            )
                        }
                    )

                    val multiplePermissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestMultiplePermissions(),
                        onResult = {
                            permissionToRequest.forEach { permission ->
                                viewModel.onPermissionResult(
                                    permission = permission,
                                    isGranted = it[permission] == true
                                )
                            }
                        }
                    )

                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp,
                            alignment = Alignment.CenterVertically
                        )
                    )
                    {
                        Button(onClick = {
                            singlePermissionLauncher.launch(Manifest.permission.CAMERA)
                        }) {
                            Text(text = "Single Permission Request")
                        }

                        Button(onClick = {
                            multiplePermissionLauncher.launch(permissionToRequest)
                        }) {
                            Text(text = "Multiple Permission Request")
                        }

                        permissionQueue.forEach { permission ->
                            PermissionDialog(
                                textProvider = when (permission) {
                                    Manifest.permission.CAMERA -> CameraTextProvider()
                                    Manifest.permission.RECORD_AUDIO -> RecordAudioTextProvider()
                                    Manifest.permission.CALL_PHONE -> PhoneCallTextProvider()
                                    else -> return@forEach
                                },
                                isPermanentlyDeclined = (!shouldShowRequestPermissionRationale(
                                    permission
                                )),
                                onDismiss = { viewModel.dismissDialog() },
                                onOkClicked = {
                                    viewModel.dismissDialog()
                                    multiplePermissionLauncher.launch(arrayOf(permission))
                                },
                                onSettingClicked = { openAppSetting() }
                            )
                        }
                    }
                }
            }
        }
    }
}

private val permissionToRequest = arrayOf(
    Manifest.permission.RECORD_AUDIO,
    Manifest.permission.CALL_PHONE
)

fun Activity.openAppSetting() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}