package com.example.jetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
class MyViewModel : ViewModel() {
    var isLoading = MutableStateFlow(false)
        private set

    init {
        loadingStuff()
    }

    fun loadingStuff() {
        viewModelScope.launch {
            isLoading.value = true
            delay(3000L)
            isLoading.value = false
        }
    }
}