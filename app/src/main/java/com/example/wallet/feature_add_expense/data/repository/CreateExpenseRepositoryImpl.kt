package com.example.wallet.feature_add_expense.data.repository

import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository
import javax.inject.Inject

class CreateExpenseRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : CreateExpenseRepository {
    override suspend fun create(expense: Expense) = expenseDao.createExpense(expense)
}