package com.example.wallet.core.data.preferences

import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.data.preferences.application.Theme
import kotlinx.coroutines.flow.flow

class FakeApplicationPreferences(
    private var last: Long? = null,
    private var theme: Theme = Theme.LIGHT
) : ApplicationPreferences {

    override suspend fun getTheme() = flow { emit(theme) }

    override suspend fun getLastUpdated() = last

    override suspend fun setTheme(theme: Theme) {
        this.theme = theme
    }

    override suspend fun setLastUpdated(date: Long) {
        last = date
    }

}