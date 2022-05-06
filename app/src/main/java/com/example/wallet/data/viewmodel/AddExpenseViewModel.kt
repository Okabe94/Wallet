package com.example.wallet.data.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.wallet.data.entity.Expense
import com.example.wallet.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    val expenses = expenseRepository.getExpenses()
    var expenseName by mutableStateOf("")
    var expenseAmount by mutableStateOf("")
    var isMonthly by mutableStateOf(false)

    private fun clearFields() {
        expenseAmount = ""
        expenseName = ""
        isMonthly = false
    }

    private fun createExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.createExpense(expense)
    }

    private fun deleteExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.deleteExpense(expense)
    }

    fun onExpenseNameChange(text: String) {
        expenseName = text
    }

    fun onExpenseAmountChange(text: String) {
        expenseAmount = text
    }

    fun onMonthlyChange(checked: Boolean) {
        isMonthly = checked
    }

    fun createNewExpense() {
        if (expenseAmount.isNotBlank() && expenseName.isNotBlank()) {
            createExpense(Expense(expenseName, expenseName, isMonthly))
            clearFields()
        }
    }
}