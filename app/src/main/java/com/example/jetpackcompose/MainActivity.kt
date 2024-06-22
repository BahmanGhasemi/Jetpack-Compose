package com.example.jetpackcompose

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.getSystemService
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                handleIntent(intent)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {

                        when (viewModel.typeState) {
                            ShortcutType.STATIC -> Text(text = "Static shortcut Clicked!")
                            ShortcutType.DYNAMIC -> Text(text = "Dynamic shortcut Clicked!")
                            ShortcutType.PINNED -> Text(text = "Pinned shortcut Clicked!")
                            null -> Unit
                        }

                        Button(onClick = ::addStaticShortcut) {
                            Text(text = "Add Static Shortcut")
                        }

                        Button(onClick = ::addPinnedShortcut) {
                            Text(text = "Add Pinned Shortcut")
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun addStaticShortcut() {
        val shortcut = ShortcutInfoCompat.Builder(applicationContext, "dynamic")
            .setShortLabel("Dynamic Shortcut")
            .setLongLabel("this is for testing Dynamic Shortcut")
            .setIcon(
                IconCompat.createWithResource(
                    applicationContext,
                    R.drawable.ic_launcher_foreground
                )
            )
            .setIntent(Intent(applicationContext, MainActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                putExtra("shortcut_id", "dynamic")
            })
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(applicationContext, shortcut)
    }

    private fun addPinnedShortcut() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return

        val shortCutManager = getSystemService<ShortcutManager>()!!
        if (shortCutManager.isRequestPinShortcutSupported) {
            val shortcutInfo = ShortcutInfo.Builder(applicationContext, "pinned")
                .setShortLabel("Quick Access")
                .setLongLabel("This is for quick access to contact!")
                .setIcon(
                    Icon.createWithResource(
                        applicationContext,
                        R.drawable.baseline_airport_shuttle_24
                    )
                )
                .setIntent(
                    Intent(applicationContext, MainActivity::class.java).apply {
                        action = Intent.ACTION_VIEW
                        putExtra("shortcut_id", "pinned")
                    }
                )
                .build()

            val callBackIntent = shortCutManager.createShortcutResultIntent(shortcutInfo)
            val pendingIntent = PendingIntent.getBroadcast(
                applicationContext,
                0,
                callBackIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            shortCutManager.requestPinShortcut(shortcutInfo, pendingIntent.intentSender)
        }

    }

    private fun handleIntent(intent: Intent?) {
        intent?.let {
            when (it.getStringExtra("shortcut_id")) {
                "static" -> viewModel.parseClicked(ShortcutType.STATIC)
                "dynamic" -> viewModel.parseClicked(ShortcutType.DYNAMIC)
                "pinned" -> viewModel.parseClicked(ShortcutType.PINNED)
            }
        }
    }
}