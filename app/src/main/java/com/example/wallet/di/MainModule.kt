package com.example.wallet.di

import com.example.wallet.data.datasource.dao.RecurrentDao
import com.example.wallet.data.datasource.database.ExpenseDatabase
import com.example.wallet.data.preferences.application.ApplicationPreferences
import com.example.wallet.data.repository.RecurrentRepositoryImpl
import com.example.wallet.domain.repository.RecurrentRepository
import com.example.wallet.domain.usecase.recurrent.CheckUseCase
import com.example.wallet.domain.usecase.recurrent.RecurrentUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MainModule {
    @Provides
    fun providesRecurrentDao(db: ExpenseDatabase) = db.recurrentDao()

    @Provides
    fun providesRecurrentRepository(
        dao: RecurrentDao
    ): RecurrentRepository = RecurrentRepositoryImpl(dao)

    @Provides
    fun providesCheckUseCase(
        repository: RecurrentRepository,
        preferences: ApplicationPreferences
    ) = CheckUseCase(repository, preferences)

    @Provides
    fun providesRecurrentUseCases(checkUseCase: CheckUseCase) = RecurrentUseCases(checkUseCase)
}