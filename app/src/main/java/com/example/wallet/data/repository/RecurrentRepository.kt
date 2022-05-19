package com.example.wallet.data.repository

import com.example.wallet.data.dao.RecurrentDao
import com.example.wallet.data.entity.Expense
import javax.inject.Inject

class RecurrentRepository @Inject constructor(
    private val dao: RecurrentDao
) {
    suspend fun getLatestRecurrent() = dao.getPendingRecurrent()
    suspend fun createRecurrent(list: List<Expense>) = dao.createRecurrentExpenses(list)
}