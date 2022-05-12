package com.example.wallet.ui.model

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.wallet.R
import com.example.wallet.ui.model.base.BottomMenuItem
import com.example.wallet.ui.model.base.FabActionButton

@Composable
fun getHomeFloatingActionButton(onClick: () -> Unit) = FabActionButton(
    Icons.Default.Add, CircleShape, stringResource(R.string.add), onClick
)

@Composable
fun getHomeBottomMenuItems(): List<BottomMenuItem> {

    return listOf(
        BottomMenuItem(
            stringResource(R.string.home),
            Icons.Default.Home,
            true,

        )
    )
}

