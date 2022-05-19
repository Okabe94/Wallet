package com.example.wallet.data.util.preferences.application

import android.content.Context
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val APP_PREFERENCES = "App_Preferences"
private val Context.dataStore by dataStore(APP_PREFERENCES, ApplicationPreferencesSerializer)

class ApplicationPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ApplicationPreferences {

    override suspend fun getTheme() = get { it.theme }

    override suspend fun setTheme(theme: Theme) {
        set { it.copy(theme = theme) }
    }

    override suspend fun getLastDateOpened() = get { it.lastDateOpened }

    override suspend fun setLastDateOpened(date: Long) {
        set { it.copy(lastDateOpened = date) }
    }

    private suspend fun set(
        action: suspend (ApplicationPreferencesModel) -> ApplicationPreferencesModel
    ) {
        context.dataStore.updateData(action)
    }

    private fun <T> get(action: (ApplicationPreferencesModel) -> T) =
        context.dataStore.data.map(action)

}