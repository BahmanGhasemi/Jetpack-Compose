package com.example.jetpackcompose._05_persistant_storage

import android.content.SharedPreferences
import javax.inject.Inject

class SessionCacheImpl @Inject constructor(
    private val pref: SharedPreferences
) : SessionCache {
    override fun saveSession(token: String) {
        pref.edit()
            .putString("session", token)
            .apply()
    }

    override fun getActiveSession(): String? {
        val token = pref.getString("session", null)
        return token
    }

    override fun clearSession() {
        pref.edit()
            .remove("session")
            .apply()
    }
}