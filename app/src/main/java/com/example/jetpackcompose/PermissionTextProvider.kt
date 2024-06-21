package com.example.jetpackcompose

interface PermissionTextProvider {
    fun getDescription(isDeclined: Boolean): String
}