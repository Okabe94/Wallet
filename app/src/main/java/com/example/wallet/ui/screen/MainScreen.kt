package com.example.wallet.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallet.R
import com.example.wallet.ui.component.AppBaseSurface
import com.example.wallet.ui.component.MainBottomBarNavItems
import com.example.wallet.ui.component.MainScaffold
import com.example.wallet.ui.model.FabActionButton
import com.example.wallet.ui.navigation.Screen
import com.example.wallet.ui.navigation.SetupNavGraph

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun MainScreenPreview() = AppBaseSurface { MainScreen() }

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val routeList = listOf(Screen.HomeScreen, Screen.ProfileScreen)

    MainScaffold(
        fab = FabActionButton(Icons.Default.Add, CircleShape, stringResource(R.string.add)) {
            navController.navigate(currentRoute, Screen.AddScreen.route)
        },
        bottomBarContent = {
            MainBottomBarNavItems(routeList, currentRoute) {
                navController.navigate(currentRoute, it)
            }
        }
    ) { SetupNavGraph(navController, it) }
}

private fun NavController.navigate(current: String?, to: String) {
    if (current != to) this.navigate(to)
}

