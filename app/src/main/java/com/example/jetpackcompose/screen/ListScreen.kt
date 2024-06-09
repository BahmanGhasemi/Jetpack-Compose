package com.example.jetpackcompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcompose.User

@Composable
fun ListScreen(navController: NavController) {
    LazyColumn {
        items(User.getUsers()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        val route = Destination.Detail.setId(it.id)
                        navController.navigate(route)
                    }
                    .background(Color.LightGray)
                    .padding(8.dp)
            ) {
                Text(text = it.name, fontWeight = FontWeight.Bold)
                Text(text = it.lastName)
            }
        }
    }
}