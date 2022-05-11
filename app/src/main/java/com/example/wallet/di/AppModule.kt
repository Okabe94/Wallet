package com.example.wallet.di

import android.app.Application
import androidx.room.Room
import com.example.wallet.data.dao.ExpenseDao
import com.example.wallet.data.database.ExpenseDatabase
import com.example.wallet.data.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "Expenses_database"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesExpensesDatabase(app: Application) = Room
        .databaseBuilder(app, ExpenseDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesExpenseDao(db: ExpenseDatabase) = db.expenseDao()

    @Provides
    fun providesExpenseRepository(dao: ExpenseDao) = ExpenseRepository(dao)
}