package com.example.wallet.feature_home.domain.repository

import com.example.wallet.core.domain.entity.Expense
import kotlinx.coroutines.flow.Flow

interface HomeExpenseRepository {
    fun getExpenses() : Flow<List<Expense>>
    fun getExpense(id: Int) : Flow<Expense>
}