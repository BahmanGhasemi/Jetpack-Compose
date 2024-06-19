package com.example.jetpackcompose._05_persistant_storage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(
    private val sessionCache: SessionCache
) : ViewModel() {

    val session
        get() = sessionCache.getActiveSession()
}