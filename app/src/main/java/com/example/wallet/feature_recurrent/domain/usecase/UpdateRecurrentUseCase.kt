package com.example.wallet.feature_recurrent.domain.usecase

import android.util.Log
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_recurrent.domain.model.time.Time
import com.example.wallet.feature_recurrent.domain.model.time.WalletTime
import com.example.wallet.feature_recurrent.domain.repository.RecurrentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import java.time.Clock

private const val TAG = "CHECK_RECURRENT_USE_CASE"

class UpdateRecurrentUseCase(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val clock: Clock
) {

    suspend operator fun invoke(scope: CoroutineScope) {
        val today = WalletTime.create(clock)

        preferences.getLastUpdated()?.let {
            val last = WalletTime.create(it)
            if (last.daysBetween(today) <= 0) return
        }

        val allPending = recurrentRepository.getPendingRecurrent(today.getTime())
        if (allPending.isEmpty()) return

        updateDueRecurrent(allPending, scope, today)
    }

    private suspend fun updateDueRecurrent(
        allPending: List<Expense>,
        scope: CoroutineScope,
        today: Time
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
        preferences.setLastUpdated(today.getTime())
    }

    private fun CoroutineScope.getExpiredAsync(it: Expense, currentDate: Time) = async {
        val months = WalletTime.create(it.createdAt).monthsBetween(currentDate)
        val nextDate = currentDate.nextMonth()
        it.copy(months = months, updatedUntil = nextDate.getTime())
    }

}