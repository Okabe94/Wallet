package com.example.wallet.di

import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.feature_add_expense.data.repository.CreateExpenseRepositoryImpl
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AddExpenseModule {

    @Provides
    fun providesCreateExpenseRepository(
        dao: ExpenseDao
    ): CreateExpenseRepository = CreateExpenseRepositoryImpl(dao)
}