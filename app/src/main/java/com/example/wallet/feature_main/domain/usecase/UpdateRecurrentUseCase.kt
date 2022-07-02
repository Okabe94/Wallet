package com.example.wallet.feature_main.domain.usecase

import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.example.wallet.feature_main.domain.time.TimeComparator
import com.example.wallet.feature_main.domain.time.TimeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class UpdateRecurrentUseCase @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val timeProvider: TimeProvider,
    private val timeComparator: TimeComparator
) {

    private val today = timeProvider.now()

    suspend operator fun invoke(scope: CoroutineScope, pending: List<Expense>) {
        val toUpdate = mutableListOf<Expense>()

        pending.onEach {
            val updated = scope.formatPendingAsync(it)
            toUpdate.add(updated.await())
        }

        runCatching {
            recurrentRepository.updateRecurrent(toUpdate)
        }.onFailure {
            throw it
        }.onSuccess { }
        preferences.setLastUpdated(today.millis())
    }

    private fun CoroutineScope.formatPendingAsync(it: Expense) = async {
        val previousUpdate = timeProvider.now(it.createdAt)
        val months = timeComparator.monthsBetween(previousUpdate, today)
        val nextDate = today.rollMonths()
        it.copy(months = months + it.months, updatedUntil = nextDate.millis())
    }
}