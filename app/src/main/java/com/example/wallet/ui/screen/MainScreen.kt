package com.example.wallet.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallet.R
import com.example.wallet.ui.component.wallet.HomeBottomBarNavItem
import com.example.wallet.ui.component.wallet.MainScaffold
import com.example.wallet.ui.component.wallet.WalletBase
import com.example.wallet.ui.model.FabActionButton
import com.example.wallet.ui.navigation.Screen
import com.example.wallet.ui.navigation.SetupNavGraph

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun MainScreenPreview() = WalletBase { MainScreen() }

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route
    val routeList = listOf(Screen.HomeScreen, Screen.ProfileScreen)

    MainScaffold(
        fab = FabActionButton(Icons.Default.Add, CircleShape, stringResource(R.string.add)) {
            navController.navigate(Screen.AddScreen.route)
        },
        bottomBarContent = { BottomBarItems(routeList, currentRoute, navController) }
    ) { SetupNavGraph(navController, it) }
}

@Composable
private fun RowScope.BottomBarItems(
    routeList: List<Screen>,
    currentRoute: String?,
    navController: NavHostController
) = routeList.forEach {
    HomeBottomBarNavItem(
        it.Icon, stringResource(it.title), currentRoute == it.route
    ) {
        navController.navigate(it.route)
    }
}
