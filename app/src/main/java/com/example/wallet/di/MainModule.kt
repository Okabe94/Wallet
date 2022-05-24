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
    fun providesClock(): Clock = Clock.systemDefaultZone()

    @Provides
    fun providesCheckUseCase(
        repository: RecurrentRepository,
        preferences: ApplicationPreferences,
        clock: Clock
    ) = UpdateRecurrentUseCase(repository, preferences, clock)

    @Provides
    fun providesRecurrentUseCases(updateRecurrentUseCase: UpdateRecurrentUseCase) =
        RecurrentUseCases(updateRecurrentUseCase)
}