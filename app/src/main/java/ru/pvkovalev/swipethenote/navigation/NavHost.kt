package ru.pvkovalev.swipethenote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.pvkovalev.swipethenote.MainViewModel
import ru.pvkovalev.swipethenote.screens.AddScreen
import ru.pvkovalev.swipethenote.screens.EditScreen
import ru.pvkovalev.swipethenote.screens.MainScreen

sealed class NavRoute(val route: String) {
    object Main : NavRoute("main_screen")
    object Add : NavRoute("add_screen")
    object Edit : NavRoute("edit_screen")
}

@Composable
fun NavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Main.route
    ) {
        composable(NavRoute.Main.route) {
            MainScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.Add.route) {
            AddScreen(
                navController = navController,
                viewModel = mViewModel,
            )
        }
        composable(NavRoute.Edit.route) {
            EditScreen(
                navController = navController,
                viewModel = mViewModel,
            )
        }
    }
}