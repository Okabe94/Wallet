package com.example.wallet.di

import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.core.data.datasource.database.ExpenseDatabase
import com.example.wallet.feature_add_expense.data.repository.ExpenseRepositoryImpl
import com.example.wallet.feature_add_expense.domain.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {
    @Provides
    fun providesExpenseDao(db: ExpenseDatabase) = db.expenseDao()

    @Provides
    fun providesExpenseRepository(dao: ExpenseDao): ExpenseRepository = ExpenseRepositoryImpl(dao)
}
