package com.example.wallet.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.wallet.ui.theme.WalletTheme

@Composable
fun WalletBase(content: @Composable () -> Unit) {
    WalletTheme { Surface(color = MaterialTheme.colors.background, content = content) }
}
