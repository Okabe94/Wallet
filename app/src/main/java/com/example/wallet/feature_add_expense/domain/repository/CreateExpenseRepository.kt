package com.example.wallet.feature_add_expense.domain.repository

import com.example.wallet.core.domain.entity.Expense
import kotlinx.coroutines.flow.Flow

fun interface CreateExpenseRepository {
    suspend fun create(expense: Expense)
}