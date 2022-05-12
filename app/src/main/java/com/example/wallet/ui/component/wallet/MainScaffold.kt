package com.example.wallet.ui.component.wallet

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallet.R
import com.example.wallet.ui.model.base.BottomMenuItem
import com.example.wallet.ui.model.base.FabActionButton

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainScaffoldSectionPreview() = WalletBase {
    MainScaffold(FabActionButton(Icons.Default.Add, CircleShape, stringResource(R.string.add)) {})
}

@Composable
fun MainScaffold(
    fab: FabActionButton,
    isDocked: Boolean = true,
    position: FabPosition = FabPosition.Center,
    bottomBarContent: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            with(fab) { BottomBarActionButton(icon, description, shape, onClick) }
        },
        isFloatingActionButtonDocked = isDocked,
        floatingActionButtonPosition = position,
        bottomBar = { BottomBar(fab.shape, bottomBarContent) },
        content = content
    )
}
