package com.example.wallet.presentation.component.base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wallet.ui.theme.WalletTheme

@Composable
fun AppBaseSurface(content: @Composable () -> Unit) = WalletTheme {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
        content = content
    )
}
