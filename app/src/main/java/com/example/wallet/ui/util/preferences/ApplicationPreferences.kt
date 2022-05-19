package com.example.wallet.ui.util.preferences

import kotlinx.coroutines.flow.Flow

interface ApplicationPreferences {
    suspend fun getTheme(): Flow<Theme>
    suspend fun setTheme(theme: Theme)
    suspend fun getLastDateOpened(): Flow<Long>
    suspend fun setLastDateOpened(date: Long)
}