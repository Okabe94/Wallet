package com.example.wallet.feature_main.domain.usecase

import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.feature_main.data.time.Time
import com.example.wallet.feature_main.domain.model.UseCaseWrapper
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import javax.inject.Inject

class GetPendingUpdateUseCase @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val timeManager: Time
) {

    suspend operator fun invoke(): UseCaseWrapper? {
        val today = timeManager.now()

        preferences.getLastUpdated()?.let {
            val last = timeManager.now(it)
            if (last.daysBetween(today) <= 0) return null
        }

        val pending = recurrentRepository.getPendingRecurrent(today.getTime())
        if (pending.isEmpty()) return null
        return UseCaseWrapper(today, pending)
    }

}