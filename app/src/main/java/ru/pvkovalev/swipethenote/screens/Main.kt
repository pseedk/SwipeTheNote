package ru.pvkovalev.swipethenote.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Main(navController: NavHostController) {
    Text(text = "Notes App")
}