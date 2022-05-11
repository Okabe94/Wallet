package com.example.wallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wallet.ui.screen.AddExpenseScreen
import com.example.wallet.ui.screen.ExpenseListScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ADD_EXPENSE_SCREEN.route
    ) {
        composable(route = Screen.HOME_SCREEN.route) {
            ExpenseListScreen(navController)
        }
        composable(route = Screen.ADD_EXPENSE_SCREEN.route) {
            AddExpenseScreen(navController)
        }
    }

}