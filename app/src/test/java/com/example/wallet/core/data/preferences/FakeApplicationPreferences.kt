package com.example.wallet.core.data.preferences

import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.data.preferences.application.Theme
import kotlinx.coroutines.flow.flow

class FakeApplicationPreferences : ApplicationPreferences {

    private var theme = Theme.LIGHT
    private var lastUpdate: Long? = null

    override suspend fun getTheme() = flow { emit(theme) }

    override suspend fun setTheme(theme: Theme) {
        this.theme = theme
    }

    override suspend fun getLastUpdated(): Long? = null

    override suspend fun setLastUpdated(date: Long) {
        lastUpdate = date
    }

}