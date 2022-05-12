package com.example.wallet.ui.model.base

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomMenuItem(
    val name: String,
    val icon: ImageVector,
    var isSelected: Boolean
)
