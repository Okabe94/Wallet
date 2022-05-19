package com.example.wallet.ui.util.preferences

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationPreferencesModel(
    val lastDateOpened: Long = 0,
    val theme: Theme = Theme.DARK
)

@Serializable
enum class Theme {
    LIGHT, DARK
}

