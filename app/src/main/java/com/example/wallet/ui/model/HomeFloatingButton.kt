package com.example.wallet.ui.model

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.example.wallet.ui.model.base.BottomBarActionButton

class HomeFloatingButton(description: String?, onClick: () -> Unit) : BottomBarActionButton(
    Icons.Default.Add, CircleShape, description, onClick
)