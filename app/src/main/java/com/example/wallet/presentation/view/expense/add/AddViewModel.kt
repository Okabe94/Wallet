package com.example.wallet.presentation.view.expense.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.wallet.data.datasource.entity.Expense
import com.example.wallet.data.repository.ExpenseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepositoryImpl
) : ViewModel() {

    var name by mutableStateOf("")
    var amount by mutableStateOf("")
    var isMonthly by mutableStateOf(false)

    private fun clearFields() {
        name = ""
        amount = ""
        isMonthly = false
    }

    private fun createExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.createExpense(expense)
    }

    fun onNameChange(text: String) {
        name = text
    }

    fun onAmountChange(text: String) {
        amount = text
    }

    fun onMonthlyChange(checked: Boolean) {
        isMonthly = checked
    }

    fun createNewExpense(navController: NavController) {
        if (amount.isNotBlank() && name.isNotBlank()) {
            createExpense(Expense(name, name, isMonthly))
            clearFields()
            navController.navigateUp()
        }
    }
}