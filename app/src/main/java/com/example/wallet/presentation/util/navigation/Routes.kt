package com.example.wallet.presentation.util.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.wallet.R

sealed class NavScreen(val title: Int, val Icon: ImageVector, route: String) : Screen(route) {
    object HomeScreen : NavScreen(R.string.home, Icons.Default.Home, "home_screen")
    object ProfileScreen : NavScreen(R.string.profile, Icons.Default.Person, "profile_screen")
}

sealed class Screen(val route: String) {
    object DetailScreen : Screen("detail_screen")
    object AddScreen : Screen("add_expense_screen")
}


