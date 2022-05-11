package com.example.wallet.ui.model.base

import androidx.compose.ui.graphics.vector.ImageVector

abstract class BottomBarMenuItem(
    val name: String,
    val icon: ImageVector,
    var isSelected: Boolean,
    val onClick: () -> Unit
)
