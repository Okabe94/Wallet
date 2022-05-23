package com.example.wallet.presentation.util.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wallet.presentation.view.expense.add.AddScreen
import com.example.wallet.presentation.view.expense.detail.DetailScreen
import com.example.wallet.presentation.view.home.HomeScreen
import com.example.wallet.presentation.view.profile.ProfileScreen

@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route
    ) {
        composable(route = NavScreen.HomeScreen.route) {
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
        composable(route = NavScreen.ProfileScreen.route) {
            ProfileScreen(paddingValues, navController)
        }
    }

}