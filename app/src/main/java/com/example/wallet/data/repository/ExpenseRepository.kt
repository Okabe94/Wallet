package com.example.wallet.data.repository

import com.example.wallet.data.dao.ExpenseDao
import com.example.wallet.data.entity.Expense
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) {
    fun getExpenses() = expenseDao.getExpenses()
    fun getExpense(id: Int) = expenseDao.getExpense(id)
    suspend fun createExpense(expense: Expense) = expenseDao.createExpense(expense)
    suspend fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)
}