package com.example.wallet.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.wallet.R

sealed class Screen(val title: Int, val Icon: ImageVector, val route: String) {
    object HomeScreen : Screen(R.string.home, Icons.Default.Home, "home_screen")
    object ProfileScreen : Screen(R.string.profile, Icons.Default.Person, "profile_screen")
    object AddExpenseScreen : Screen(R.string.add, Icons.Default.Add, "add_expense_screen")
}


