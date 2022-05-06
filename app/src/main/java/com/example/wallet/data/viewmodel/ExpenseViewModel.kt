package com.example.wallet.data.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.entity.Expense
import com.example.wallet.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
): ViewModel() {

    fun createExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.createExpense(expense)
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.deleteExpense(expense)
    }

    fun getExpenses() = expenseRepository.getExpenses().asLiveData()

    fun getExpense(id: Int) = expenseRepository.getExpense(id).asLiveData()
}