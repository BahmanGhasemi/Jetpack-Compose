package com.example.jetpackcompose.screen

sealed class Destination(val route: String) {
    object List : Destination("List")
    object Detail : Destination("Detail/{userId}") {
        fun setId(id: Int) = "Detail/$id"
    }
}