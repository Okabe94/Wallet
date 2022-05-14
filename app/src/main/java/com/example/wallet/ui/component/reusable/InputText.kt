package com.example.wallet.ui.component.reusable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun WalletInputText(
    modifier: Modifier = Modifier,
    name: String,
    value: String,
    leadingIcon: ImageVector? = null,
    description: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    options: KeyboardOptions = KeyboardOptions.Default,
    onChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onChange,
        singleLine = true,
        placeholder = { Text(text = name) },
        label = { Text(text = name) },
        keyboardOptions = options,
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = description) }
        },
        visualTransformation = visualTransformation
    )
}