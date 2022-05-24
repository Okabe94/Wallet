package com.example.wallet.feature_add_expense.domain.repository

import com.example.wallet.feature_add_expense.domain.entity.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses() : Flow<List<Expense>>
    fun getExpense(id: Int) : Flow<Expense>
    suspend fun createExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
}