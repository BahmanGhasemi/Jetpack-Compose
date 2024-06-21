package com.example.jetpackcompose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PermissionHandlerViewModel : ViewModel() {
    val permissions = mutableStateListOf<String>()

    fun dismissDialog() {
        permissions.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !permissions.contains(permission)) {
            permissions.add(permission)
        }
    }
}