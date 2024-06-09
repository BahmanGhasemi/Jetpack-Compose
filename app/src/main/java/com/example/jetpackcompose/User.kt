package com.example.jetpackcompose

data class User(
    val id: Int,
    val name: String,
    val lastName: String
) {
    companion object {
        private val users = listOf(
            User(1, "Bahman", "Ghasemi"),
            User(2, "Ali", "Zare"),
            User(3, "Sadegh", "Hashemi"),
            User(4, "Kamran", "Palang"),
            User(5, "Hanieh", "Majidi"),
            User(6, "Reza", "Dx"),
            User(7, "Abbas", "Palang"),
        )

        fun getUsers() = users
        fun getUser(id: Int) = getUsers().find { it.id == id }
    }
}
