package ru.pvkovalev.swipethenote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.pvkovalev.swipethenote.MainViewModel
import ru.pvkovalev.swipethenote.screens.AddScreen
import ru.pvkovalev.swipethenote.screens.EditScreen
import ru.pvkovalev.swipethenote.screens.MainScreen
import ru.pvkovalev.swipethenote.utils.Constants.Keys.ID
import ru.pvkovalev.swipethenote.utils.Constants.Screens.ADD_SCREEN
import ru.pvkovalev.swipethenote.utils.Constants.Screens.EDIT_SCREEN
import ru.pvkovalev.swipethenote.utils.Constants.Screens.MAIN_SCREEN

sealed class NavRoute(val route: String) {
    object Main : NavRoute(MAIN_SCREEN)
    object Add : NavRoute(ADD_SCREEN)
    object Edit : NavRoute(EDIT_SCREEN)
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
        composable(NavRoute.Edit.route + "/{${ID}}") { backStackEntry ->
            EditScreen(
                navController = navController,
                viewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(ID)
            )
        }
    }
}