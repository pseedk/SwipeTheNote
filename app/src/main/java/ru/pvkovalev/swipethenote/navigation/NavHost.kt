package ru.pvkovalev.swipethenote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.pvkovalev.swipethenote.screens.Add
import ru.pvkovalev.swipethenote.screens.Edit
import ru.pvkovalev.swipethenote.screens.Main

sealed class NavRoute(val route: String) {
    object Main : NavRoute("main_screen")
    object Add : NavRoute("add_screen")
    object Edit : NavRoute("edit_screen")
}

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Main.route
    ) {
        composable(NavRoute.Main.route) {
            Main(
                navController = navController
            )
        }
        composable(NavRoute.Add.route) {
            Add(
                navController = navController
            )
        }
        composable(NavRoute.Edit.route) {
            Edit(
                navController = navController
            )
        }
    }
}