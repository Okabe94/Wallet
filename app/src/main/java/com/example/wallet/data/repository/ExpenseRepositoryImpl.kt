package com.example.wallet.data.repository

import com.example.wallet.data.datasource.dao.ExpenseDao
import com.example.wallet.data.datasource.entity.Expense
import com.example.wallet.domain.repository.ExpenseRepository
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    override fun getExpenses() = expenseDao.getExpenses()
    override fun getExpense(id: Int) = expenseDao.getExpense(id)
    override suspend fun createExpense(expense: Expense) = expenseDao.createExpense(expense)
    override suspend fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)

}