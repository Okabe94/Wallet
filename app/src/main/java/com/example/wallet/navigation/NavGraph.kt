package com.example.wallet.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallet.presentation.screen.AddScreen
import com.example.wallet.presentation.screen.DetailScreen
import com.example.wallet.presentation.screen.HomeScreen
import com.example.wallet.presentation.screen.ProfileScreen

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
        composable(
            "${Screen.DetailScreen.route}/{id}",
            listOf(navArgument("id") { type = NavType.IntType })
        ) {
            DetailScreen(paddingValues, navController, id = it.arguments?.getInt("id"))
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(paddingValues, navController)
        }
    }

}