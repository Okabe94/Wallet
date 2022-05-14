package com.example.wallet.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallet.ui.screen.AddScreen
import com.example.wallet.ui.screen.DetailScreen
import com.example.wallet.ui.screen.HomeScreen
import com.example.wallet.ui.screen.ProfileScreen

@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(paddingValues, navController)
        }
        composable(route = Screen.AddScreen.route) {
            AddScreen(paddingValues, navController)
        }
        composable(route = "${Screen.DetailScreen.route}/{id}", listOf(
            navArgument("id") { type = NavType.IntType }
        )) {
            DetailScreen(paddingValues, navController, id = it.arguments?.getInt("id") ?: 0)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(paddingValues, navController)
        }
    }

}