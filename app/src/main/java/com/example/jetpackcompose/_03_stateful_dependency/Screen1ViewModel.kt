package com.example.jetpackcompose._03_stateful_dependency

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(
    private val counter: GlobalCounter
) : ViewModel() {

    val count = counter.count
    fun inc(){
        counter.increment()
    }
}