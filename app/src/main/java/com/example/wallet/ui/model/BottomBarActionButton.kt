package com.example.wallet.ui.model

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarActionButton(
    val icon: ImageVector,
    val shape: Shape,
    val description: String?,
    val onClick: () -> Unit
)

