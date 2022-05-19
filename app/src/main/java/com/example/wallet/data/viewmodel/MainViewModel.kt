package com.example.wallet.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.entity.Expense
import com.example.wallet.data.repository.RecurrentRepository
import com.example.wallet.data.util.dispatcher.ApplicationDispatcher
import com.example.wallet.data.util.preferences.application.ApplicationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val dispatcher: ApplicationDispatcher,
    private val preferences: ApplicationPreferences
) : ViewModel() {

    private val currentDate = Calendar.getInstance()

    fun checkForRecurring() {
        viewModelScope.launch(dispatcher.io) {
            val newPending = mutableListOf<Expense>()

            val oldPending = recurrentRepository.getPendingRecurrent().onEach {
                val pendingExpenses = async {
                    val expenses = mutableListOf<Expense>()
                    val nextDate = Calendar.getInstance().apply {
                        timeInMillis = it.date
                        rollAMonth()
                    }

                    while (currentDate > nextDate) {
                        expenses.add(createNewEntry(it, nextDate.timeInMillis))
                        nextDate.rollAMonth()
                    }
                    expenses
                }
                newPending.addAll(pendingExpenses.await())
            }
            val updatedOldPending = oldPending.map { it.copy(recurrentUpdated = true) }
            runCatching {
                recurrentRepository.updateAndCreatePendingRecurrent(
                    newPending,
                    updatedOldPending
                )
            }.onFailure {
                Log.e(TAG, "Error insertando los valores nuevos recurrentes")
                Log.e(TAG, it.message, it.cause)
            }.onSuccess {
                Log.d(TAG, "Se insertaron todos los valores nuevos")
            }
        }
    }

    private fun createNewEntry(item: Expense, date: Long) =
        Expense(item.name, item.amount, item.isMonthly, date)

    private fun Calendar.rollAMonth() = roll(Calendar.MONTH, 1)
}
