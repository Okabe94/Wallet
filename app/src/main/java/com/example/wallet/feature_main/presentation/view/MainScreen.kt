package com.example.wallet.feature_main.presentation.view

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallet.R
import com.example.wallet.core.presentation.component.AppBaseSurface
import com.example.wallet.feature_main.presentation.view.component.MainBottomBarNavItems
import com.example.wallet.feature_main.presentation.view.component.MainScaffold
import com.example.wallet.feature_add_expense.presentation.model.FabActionButton
import com.example.wallet.core.presentation.util.navigation.NavScreen
import com.example.wallet.core.presentation.util.navigation.Screen
import com.example.wallet.core.presentation.util.navigation.SetupNavGraph

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun MainScreenPreview() = AppBaseSurface { MainScreen() }

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val routeList = listOf(NavScreen.HomeScreen, NavScreen.ProfileScreen)

    MainScaffold(
        fab = FabActionButton(Icons.Default.Add, CircleShape, stringResource(R.string.add)) {
            navController.navigate(
                navController.graph.startDestinationId,
                currentRoute,
                Screen.AddScreen.route
            )
        },
        bottomBarContent = {
            MainBottomBarNavItems(routeList, currentRoute) {
                navController.navigate(navController.graph.startDestinationId, currentRoute, it)
            }
        }
    ) { SetupNavGraph(navController, it) }
}

private fun NavController.navigate(startingScreen: Int, current: String?, to: String) {
    if (current != to) navigate(to) {
        popUpTo(startingScreen)
        launchSingleTop = true
    }
}

