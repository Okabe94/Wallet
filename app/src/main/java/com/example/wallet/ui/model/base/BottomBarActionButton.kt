package com.example.wallet.ui.model.base

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

abstract class BottomBarActionButton(
    val icon: ImageVector,
    val shape: Shape,
    val description: String?,
    val onClick: () -> Unit
)

