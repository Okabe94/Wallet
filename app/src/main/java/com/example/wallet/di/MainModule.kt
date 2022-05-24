package com.example.wallet.di

import com.example.wallet.feature_recurrent.data.datasource.dao.RecurrentDao
import com.example.wallet.core.data.datasource.database.ExpenseDatabase
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.feature_recurrent.data.repository.RecurrentRepositoryImpl
import com.example.wallet.feature_recurrent.domain.repository.RecurrentRepository
import com.example.wallet.feature_recurrent.domain.usecase.UpdateRecurrentUseCase
import com.example.wallet.feature_recurrent.domain.usecase.RecurrentUseCases
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
    ) = UpdateRecurrentUseCase(repository, preferences)

    @Provides
    fun providesRecurrentUseCases(updateRecurrentUseCase: UpdateRecurrentUseCase) = RecurrentUseCases(updateRecurrentUseCase)
}