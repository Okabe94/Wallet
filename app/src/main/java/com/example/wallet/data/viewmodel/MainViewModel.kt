package com.example.wallet.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.entity.Expense
import com.example.wallet.data.repository.ExpenseRepository
import com.example.wallet.data.repository.RecurrentRepository
import com.example.wallet.data.util.dispatcher.ApplicationDispatcher
import com.example.wallet.data.util.preferences.application.ApplicationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val dispatcher: ApplicationDispatcher,
    private val preferences: ApplicationPreferences
) : ViewModel() {

    private val currentDate = Calendar.getInstance()

    fun checkForRecurring() {
        viewModelScope.launch(dispatcher.io) {
            val missingMonthlyExpenses = mutableListOf<Expense>()

            recurrentRepository.getLatestRecurrent().forEach {
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
                missingMonthlyExpenses.addAll(pendingExpenses.await())
            }
            recurrentRepository.createRecurrent(missingMonthlyExpenses)
        }
    }

    private fun createNewEntry(item: Expense, date: Long) =
        Expense(item.name, item.amount, item.isMonthly, date)

    private fun Calendar.rollAMonth() = roll(Calendar.MONTH, 1)
}
