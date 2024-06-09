package com.example.jetpackcompose

sealed class Destination(val route: String) {
    object Home : Destination("Home")
    object Feed : Destination("Feed")
    object Profile : Destination("Profile")
}