package com.example.wallet.data.repository

import com.example.wallet.data.dao.RecurrentDao
import com.example.wallet.data.entity.Expense
import javax.inject.Inject

class RecurrentRepository @Inject constructor(
    private val dao: RecurrentDao
) {
    suspend fun getPendingRecurrent() = dao.getPendingRecurrent()
    suspend fun updateAndCreatePendingRecurrent(
        newPending: List<Expense>,
        oldPending: List<Expense>
    ) = dao.updateAndCreatePendingRecurrent(newPending, oldPending)
}