package com.example.wallet.di

import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository
import com.example.wallet.feature_add_expense.domain.usecase.AddExpenseUseCase
import com.example.wallet.feature_add_expense.domain.usecase.AddUseCases
import com.example.wallet.feature_home.data.repository.HomeExpenseRepositoryImpl
import com.example.wallet.feature_home.domain.repository.HomeExpenseRepository
import com.example.wallet.feature_main.data.time.Time
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun providesHomeExpenseRepository(
        dao: ExpenseDao
    ): HomeExpenseRepository = HomeExpenseRepositoryImpl(dao)

    @Provides
    fun providesAddUseCase(
        repository: CreateExpenseRepository,
        timeManager: Time
    ) = AddExpenseUseCase(repository, timeManager)

    @Provides
    fun providesAddUseCases(addUseCase: AddExpenseUseCase) = AddUseCases(addUseCase)
}
