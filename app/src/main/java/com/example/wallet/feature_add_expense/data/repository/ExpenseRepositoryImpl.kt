package com.example.wallet.feature_add_expense.data.repository

import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.ExpenseRepository
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    override fun getExpenses() = expenseDao.getExpenses()
    override fun getExpense(id: Int) = expenseDao.getExpense(id)
    override suspend fun createExpense(expense: Expense) = expenseDao.createExpense(expense)
    override suspend fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)

}