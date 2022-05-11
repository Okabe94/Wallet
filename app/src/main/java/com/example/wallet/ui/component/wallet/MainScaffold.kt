package com.example.wallet.ui.component.wallet

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallet.R
import com.example.wallet.ui.model.HomeBottomBarItem
import com.example.wallet.ui.model.HomeFloatingButton
import com.example.wallet.ui.model.base.BottomBarActionButton
import com.example.wallet.ui.model.base.BottomBarMenuItem

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScaffoldSectionPreview() = WalletBase {
    MainScaffold(
        floating = HomeFloatingButton(stringResource(R.string.add)) {},
        menuItems = listOf(
            HomeBottomBarItem(
                stringResource(R.string.home),
                Icons.Default.Home,
                false
            ) {}, HomeBottomBarItem(
                stringResource(R.string.user),
                Icons.Default.Person,
                false
            ) {}
        )
    )
}

@Composable
fun MainScaffold(
    floating: BottomBarActionButton,
    menuItems: List<BottomBarMenuItem>,
    isDocked: Boolean = true,
    position: FabPosition = FabPosition.Center,
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            with(floating) { BottomBarActionButton(icon, description, shape, onClick) }
        },
        isFloatingActionButtonDocked = isDocked,
        floatingActionButtonPosition = position,
        bottomBar = { BottomBar(floating.shape, menuItems) },
        content = content
    )
}
