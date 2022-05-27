package com.example.wallet.di

import com.example.wallet.core.data.datasource.database.ExpenseDatabase
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.feature_main.data.datasource.dao.RecurrentDao
import com.example.wallet.feature_main.data.repository.RecurrentRepositoryImpl
import com.example.wallet.feature_main.domain.model.time.Time
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.example.wallet.feature_main.domain.usecase.GetPendingUpdateUseCase
import com.example.wallet.feature_main.domain.usecase.RecurrentUseCases
import com.example.wallet.feature_main.domain.usecase.UpdateRecurrentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.time.Clock

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
    fun providesUpdateRecurrentUseCase(
        repository: RecurrentRepository,
        preferences: ApplicationPreferences,
        timeManager: Time
    ) = UpdateRecurrentUseCase(repository, preferences, timeManager)

    @Provides
    fun providesGetRecurrentUseCase(
        repository: RecurrentRepository,
        preferences: ApplicationPreferences,
        timeManager: Time,
        clock: Clock
    ) = GetPendingUpdateUseCase(repository, preferences, clock, timeManager)

    @Provides
    fun providesRecurrentUseCases(
        updateRecurrentUseCase: UpdateRecurrentUseCase,
        getPendingUpdateUseCase: GetPendingUpdateUseCase
    ) =
        RecurrentUseCases(updateRecurrentUseCase, getPendingUpdateUseCase)
}