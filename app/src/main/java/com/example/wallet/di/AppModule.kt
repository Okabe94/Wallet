package com.example.wallet.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wallet.data.datasource.dao.ExpenseDao
import com.example.wallet.data.datasource.dao.RecurrentDao
import com.example.wallet.data.datasource.database.ExpenseDatabase
import com.example.wallet.data.repository.ExpenseRepositoryImpl
import com.example.wallet.data.repository.RecurrentRepositoryImpl
import com.example.wallet.data.util.Constants
import com.example.wallet.data.util.dispatcher.ApplicationDispatcher
import com.example.wallet.data.util.dispatcher.ApplicationDispatcherImpl
import com.example.wallet.domain.repository.ExpenseRepository
import com.example.wallet.domain.repository.RecurrentRepository
import com.example.wallet.data.preferences.application.ApplicationPreferences
import com.example.wallet.data.preferences.application.ApplicationPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
}