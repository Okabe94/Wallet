package com.example.wallet.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarMenuItem(
    val name: String,
    val icon: ImageVector,
    var isSelected: Boolean,
    val onClick: () -> Unit
)
