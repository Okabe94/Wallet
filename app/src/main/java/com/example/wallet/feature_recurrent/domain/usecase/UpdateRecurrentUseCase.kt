package com.example.wallet.feature_recurrent.domain.usecase

import android.util.Log
import com.example.wallet.feature_add_expense.domain.entity.Expense
import com.example.wallet.core.data.preferences.application.ApplicationPreferences
import com.example.wallet.feature_recurrent.domain.repository.RecurrentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import java.util.*

private const val TAG = "CHECK_RECURRENT_USE_CASE"

class UpdateRecurrentUseCase(
    private val recurrentRepository: RecurrentRepository,
    private val preferences: ApplicationPreferences
) {

    suspend operator fun invoke(scope: CoroutineScope) {
        val today = Calendar.getInstance()
        val dueExpenses = mutableListOf<Expense>()

        val allPending = recurrentRepository.getPendingRecurrent().onEach {
            val expiredList = scope.getExpiredAsync(it, today)
            dueExpenses.addAll(expiredList.await())
        }

        val toUpdate = allPending.map { it.copy(recurrentUpdated = true) }

        runCatching {
            recurrentRepository.updateAndCreatePendingRecurrent(dueExpenses, toUpdate)
        }.onFailure {
            // Add Failure state
            Log.e(TAG, "Error insertando los valores nuevos recurrentes")
            Log.e(TAG, it.message, it.cause)
        }.onSuccess {
            // Add Success state
            Log.d(TAG, "Se insertaron todos los valores nuevos")
        }
    }

    private fun CoroutineScope.getExpiredAsync(it: Expense, currentDate: Calendar) = async {
        val expiredList = mutableListOf<Expense>()
        val nextDate = Calendar.getInstance().apply {
            timeInMillis = it.date
            rollAMonth()
        }

        while (currentDate > nextDate) {
            expiredList.add(createNewEntry(it, nextDate.timeInMillis))
            nextDate.rollAMonth()
        }
        expiredList
    }

    private fun createNewEntry(item: Expense, date: Long) = Expense(
        item.name, item.amount, item.isMonthly, date
    )

    private fun Calendar.rollAMonth() = roll(Calendar.MONTH, 1)
}