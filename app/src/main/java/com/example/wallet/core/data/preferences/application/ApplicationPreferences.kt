package com.example.wallet.core.data.preferences.application

import kotlinx.coroutines.flow.Flow

interface ApplicationPreferences {
    suspend fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
    suspend fun getLastUpdated(): Long?
    suspend fun setLastUpdated(date: Long)
}