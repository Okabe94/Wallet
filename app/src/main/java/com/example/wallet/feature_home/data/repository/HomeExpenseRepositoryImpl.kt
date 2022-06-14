package com.example.wallet.feature_home.data.repository

import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.feature_home.domain.repository.HomeExpenseRepository
import javax.inject.Inject

class HomeExpenseRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : HomeExpenseRepository {

    override fun getExpenses() = expenseDao.getExpenses()
    override fun getExpense(id: Int) = expenseDao.getExpense(id)
}