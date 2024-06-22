package com.example.jetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var typeState by mutableStateOf<ShortcutType?>(null)
        private set

    fun parseClicked(type: ShortcutType) {
        typeState = type
    }

}

enum class ShortcutType {
    STATIC, DYNAMIC, PINNED
}