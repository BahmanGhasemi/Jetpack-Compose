package com.example.jetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _persons = MutableStateFlow(allPersons())
    val persons = searchText
        .debounce(500L)
        .onEach { _isLoading.value = true }
        .combine(_persons) { text, persons ->
            if (text.isBlank())
                persons
            else
                persons.filter {
                    delay(200L)
                    it.isMatchingToQuery(text)
                }
        }
        .onEach { _isLoading.value = false }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            initialValue = _persons.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}

data class Person(
    val firstName: String,
    val lastName: String
) {
    fun isMatchingToQuery(query: String): Boolean {
        val matcherList = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}"
        )

        return matcherList.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private fun allPersons() = listOf(
    Person("Bahman", "Ghasemi"),
    Person("Ali", "Zare"),
    Person("Kamran", "Palang"),
    Person("Sadegh", "Hashemi"),
    Person("Reza", "Dx"),
)