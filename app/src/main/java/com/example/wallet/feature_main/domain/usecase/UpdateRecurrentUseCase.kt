package com.example.wallet.feature_main.domain.usecase

import android.util.Log
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.domain.model.wrapper.UseCaseWrapper
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import com.example.wallet.feature_main.domain.time.Time
import com.example.wallet.feature_main.domain.time.TimeComparator
import com.example.wallet.feature_main.domain.time.TimeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

private const val TAG = "UPDATE_RECURRENT_USE_CASE"

class UpdateRecurrentUseCase @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val timeProvider: TimeProvider,
    private val timeComparator: TimeComparator
) {

    suspend operator fun invoke(scope: CoroutineScope, wrapper: UseCaseWrapper) = with(wrapper) {
        val toUpdate = mutableListOf<Expense>()

        pending.onEach {
            val updated = scope.formatPendingAsync(it, time)
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
        preferences.setLastUpdated(time.millis())
    }

    private fun CoroutineScope.formatPendingAsync(it: Expense, today: Time) = async {
        val now = timeProvider.now(it.createdAt)
        val months = timeComparator.monthsBetween(now, today)
        val nextDate = today.rollMonths()
        it.copy(months = months, updatedUntil = nextDate.millis())
    }
}