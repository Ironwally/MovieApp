package com.example.bonustask_alpha.ui

import DetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bonustask_alpha.ui.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    baseUrl: String
) {
    NavHost(navController = navController, startDestination = "search") {
        composable("search") {
            SearchScreen(
                baseUrl = baseUrl,
                onNavigateToDetail = { movieId ->
                    navController.navigate("detail/$movieId")
                }
            )
        }

        composable("detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: 0

            DetailScreen(
                movieId = movieId,
                baseUrl = baseUrl,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}