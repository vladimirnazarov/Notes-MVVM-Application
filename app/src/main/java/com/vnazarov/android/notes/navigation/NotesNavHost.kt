package com.vnazarov.android.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vnazarov.android.notes.MainViewModel
import com.vnazarov.android.notes.screens.*
import com.vnazarov.android.notes.utils.Constants.Keys.ID
import com.vnazarov.android.notes.utils.Constants.Screens.ADD_SCREEN
import com.vnazarov.android.notes.utils.Constants.Screens.MAIN_SCREEN
import com.vnazarov.android.notes.utils.Constants.Screens.NOTE_SCREEN
import com.vnazarov.android.notes.utils.Constants.Screens.START_SCREEN

sealed class NavRoute(val route: String) {
    object Start : NavRoute(START_SCREEN)
    object Main : NavRoute(MAIN_SCREEN)
    object Add : NavRoute(ADD_SCREEN)
    object Note : NavRoute(NOTE_SCREEN)
}

@Composable
fun NotesNavHost(mainViewModel: MainViewModel, navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = mainViewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = mainViewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = mainViewModel) }
        composable(NavRoute.Note.route + "/{${ID}}") { backStackEntry ->
            NoteScreen(navController = navController, viewModel = mainViewModel, noteID = backStackEntry.arguments?.getString(ID))
        }
    }
}