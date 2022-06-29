package com.example.wallet.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.wallet.core.data.datasource.database.ExpenseDatabase
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.data.preferences.application.ApplicationPreferencesImpl
import com.example.wallet.core.data.util.Constants
import com.example.wallet.core.presentation.util.dispatcher.ApplicationDispatcher
import com.example.wallet.core.presentation.util.dispatcher.ApplicationDispatcherImpl
import com.example.wallet.feature_main.data.time.DefaultTimeComparator
import com.example.wallet.feature_main.data.time.DefaultTimeProvider
import com.example.wallet.feature_main.domain.time.TimeComparator
import com.example.wallet.feature_main.domain.time.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesExpensesDatabase(app: Application) = Room
        .databaseBuilder(app, ExpenseDatabase::class.java, Constants.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesPreferences(
        @ApplicationContext context: Context
    ): ApplicationPreferences = ApplicationPreferencesImpl(context)

    @Provides
    fun providesWalletDispatcher(): ApplicationDispatcher = ApplicationDispatcherImpl()

    @Provides
    fun providesDefaultClock(): Clock = Clock.systemDefaultZone()

    @Provides
    fun providesTimeProvider(clock: Clock): TimeProvider = DefaultTimeProvider(clock)

    @Provides
    @Singleton
    fun providesTimeComparator(): TimeComparator = DefaultTimeComparator()
}