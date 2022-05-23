package com.example.wallet.domain.repository

import com.example.wallet.data.datasource.entity.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses() : Flow<List<Expense>>
    fun getExpense(id: Int) : Flow<Expense>
    suspend fun createExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
}