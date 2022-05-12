package com.example.wallet.ui.component.wallet

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(cutout: Shape?, content: @Composable RowScope.() -> Unit) = BottomAppBar(
    modifier = Modifier.fillMaxWidth(),
    backgroundColor = MaterialTheme.colors.surface,
    cutoutShape = cutout,
    contentColor = MaterialTheme.colors.onSurface,
    elevation = 5.dp,
    content = content
)

@Composable
fun RowScope.HomeBottomBarNavItem(
    image: ImageVector,
    description: String? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit
) = BottomNavigationItem(
    icon = {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = image,
            contentDescription = description
        )
    },
    selected = isSelected,
    onClick = onClick
)

@Composable
fun BottomBarActionButton(
    icon: ImageVector,
    description: String?,
    shape: Shape,
    onClick: () -> Unit
) = FloatingActionButton(
    backgroundColor = MaterialTheme.colors.primary,
    onClick = onClick,
    shape = shape
) {
    Icon(imageVector = icon, contentDescription = description)
}