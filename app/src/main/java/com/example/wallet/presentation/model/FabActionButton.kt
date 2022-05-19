package com.example.wallet.presentation.model

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

data class FabActionButton(
    val icon: ImageVector,
    val shape: Shape,
    val description: String?,
    val onClick: () -> Unit
)

