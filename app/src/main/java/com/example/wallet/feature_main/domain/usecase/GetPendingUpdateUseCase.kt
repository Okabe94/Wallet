package com.example.wallet.feature_main.domain.usecase

import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.feature_main.domain.model.wrapper.UseCaseWrapper
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.example.wallet.feature_main.domain.time.TimeComparator
import com.example.wallet.feature_main.domain.time.TimeProvider
import javax.inject.Inject

class GetPendingUpdateUseCase @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val timeProvider: TimeProvider,
    private val timeComparator : TimeComparator
) {

    suspend operator fun invoke(): UseCaseWrapper? {
        val today = timeProvider.now()

        preferences.getLastUpdated()?.let {
            val last = timeProvider.now(it)
            if (timeComparator.daysBetween(last, today) <= 0) return null
        }

        val pending = recurrentRepository.getPendingRecurrent(today.millis())
        if (pending.isEmpty()) return null
        return UseCaseWrapper(today, pending)
    }

}