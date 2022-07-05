package com.example.wallet.feature_add_expense.data.repository

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository

class FakeCreateExpenseRepository : CreateExpenseRepository {

    val list = mutableListOf<Expense>()

    override suspend fun create(expense: Expense) {
        list.add(expense)
    }
}