package com.example.wallet.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val CompactWidth = 600
private const val MediumWidth = 840
private const val CompactHeight = 480
private const val MediumHeight = 900

@Composable
fun rememberWindowInfo(): WindowInfo = with(LocalConfiguration.current) {
    WindowInfo(
        getWindowType(screenWidthDp, CompactWidth, MediumWidth),
        getWindowType(screenHeightDp, CompactHeight, MediumHeight),
        screenWidthDp.dp,
        screenHeightDp.dp
    )
}

private fun getWindowType(screenSize: Int, compactSize: Int, mediumSize: Int) = when {
    screenSize < compactSize -> WindowType.Compact
    screenSize < mediumSize -> WindowType.Medium
    else -> WindowType.Extended
}

data class WindowInfo(
    val widthInfo: WindowType,
    val heightInfo: WindowType,
    val width: Dp,
    val height: Dp
)

sealed class WindowType {
    object Compact : WindowType()
    object Medium : WindowType()
    object Extended : WindowType()
}