package com.example.wallet.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.entity.Expense
import com.example.wallet.data.repository.ExpenseRepository
import com.example.wallet.data.repository.RecurrentRepository
import com.example.wallet.data.util.dispatcher.ApplicationDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recurrentRepository: RecurrentRepository,
    private val expenseRepository: ExpenseRepository,
    private val dispatcher: ApplicationDispatcher
) : ViewModel() {

    private val lastDate = 0L
    private val currentDate = Calendar.getInstance()

    fun checkForRecurring() {
        viewModelScope.launch(dispatcher.io) {
            val latest = if (lastDate == 0L) recurrentRepository.getLatestRecurrent()
            else recurrentRepository.getLatestRecurrent(lastDate)

            latest.forEach {
                val nextDate = Calendar.getInstance().apply {
                    timeInMillis = it.date
                    rollAMonth()
                }

                while (currentDate > nextDate) {
                    expenseRepository.createExpense(createNewEntry(it, nextDate.timeInMillis))
                    nextDate.rollAMonth()
                }
            }
        }
    }

    private fun createNewEntry(item: Expense, date: Long) =
        Expense(item.name, item.amount, item.isMonthly, date)

    private fun Calendar.rollAMonth() = roll(Calendar.MONTH, 1)
}
