package com.example.wallet.feature_add_expense.data.repository

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository

class FaultyCreateExpenseRepository : CreateExpenseRepository {
    override suspend fun create(expense: Expense) {
        throw Exception()
    }
}