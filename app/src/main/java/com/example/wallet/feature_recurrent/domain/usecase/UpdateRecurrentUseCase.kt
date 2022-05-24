package com.example.wallet.feature_recurrent.domain.usecase

import android.util.Log
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_recurrent.domain.repository.RecurrentRepository
import com.example.wallet.feature_recurrent.domain.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import java.time.Clock
import java.time.Instant

private const val TAG = "CHECK_RECURRENT_USE_CASE"

class UpdateRecurrentUseCase(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val clock: Clock
) {

    suspend operator fun invoke(scope: CoroutineScope) {
        val today = standardTime(clock)

        preferences.getLastUpdated()?.let {
            val last = standardTime(it)
            if (daysBetween(last, today) <= 0) return
        }

        val allPending = recurrentRepository.getPendingRecurrent(today.requiredTime())
        if (allPending.isEmpty()) return

        updateDueRecurrent(allPending, scope, today)
    }

    private suspend fun updateDueRecurrent(
        allPending: List<Expense>,
        scope: CoroutineScope,
        today: Instant
    ) {
        val toUpdate = mutableListOf<Expense>()

        allPending.onEach {
            val updated = scope.getExpiredAsync(it, today)
            toUpdate.add(updated.await())
        }

        runCatching {
            recurrentRepository.updateRecurrent(toUpdate)
        }.onFailure {
            Log.e(TAG, "Error insertando los valores nuevos recurrentes")
            Log.e(TAG, it.message, it.cause)
        }.onSuccess {
            Log.d(TAG, "Se insertaron todos los valores nuevos")
        }
        preferences.setLastUpdated(today.requiredTime())
    }

    private fun CoroutineScope.getExpiredAsync(it: Expense, currentDate: Instant) = async {
        val months = monthsBetween(standardTime(it.createdAt), currentDate)
        val nextDate = currentDate.nextMonth()
        it.copy(months = months, updatedUntil = nextDate.requiredTime())
    }

}