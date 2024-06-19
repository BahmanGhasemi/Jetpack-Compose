package com.example.jetpackcompose._05_persistant_storage

interface SessionCache {
    fun saveSession(token:String)
    fun getActiveSession(): String?
    fun clearSession()
}