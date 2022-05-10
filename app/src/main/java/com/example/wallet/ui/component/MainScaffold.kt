package com.example.wallet.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wallet.ui.model.BottomBarActionButton
import com.example.wallet.ui.model.BottomBarMenuItem

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
