package com.example.wallet.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallet.ui.screen.AddScreen
import com.example.wallet.ui.screen.HomeScreen
import com.example.wallet.ui.screen.ProfileScreen

@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues? = null) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, paddingValues = paddingValues)
        }
        composable(route = Screen.AddScreen.route) {
            AddScreen(navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
    }

}