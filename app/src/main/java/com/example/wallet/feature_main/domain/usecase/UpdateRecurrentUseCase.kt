package com.example.wallet.feature_main.domain.usecase

import android.util.Log
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.domain.model.time.Time
import com.example.wallet.feature_main.domain.model.wrapper.UseCaseWrapper
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

private const val TAG = "UPDATE_RECURRENT_USE_CASE"

class UpdateRecurrentUseCase @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences,
    private val timeManager: Time
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
        preferences.setLastUpdated(time.getTime())
    }

    private fun CoroutineScope.formatPendingAsync(it: Expense, today: Time) = async {
        val months = timeManager.now(it.createdAt).monthsBetween(today)
        val nextDate = today.nextMonth()
        it.copy(months = months, updatedUntil = nextDate.getTime())
    }
}