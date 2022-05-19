package com.example.wallet.data.util.preferences.application

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationPreferencesModel(
    val lastDateOpened: Long? = null,
    val theme: Theme = Theme.DARK
)

@Serializable
enum class Theme {
    LIGHT, DARK
}

