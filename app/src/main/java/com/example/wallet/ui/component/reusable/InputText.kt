package com.example.wallet.ui.component.reusable

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WalletInputText(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: @Composable () -> Unit = { Text(text = value) },
    label: @Composable () -> Unit = { Text(text = value) },
    onChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onChange,
        placeholder = placeHolder,
        label = label
    )
}