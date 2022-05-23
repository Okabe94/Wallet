package com.example.wallet.data.repository

import com.example.wallet.data.datasource.dao.RecurrentDao
import com.example.wallet.data.datasource.entity.Expense
import com.example.wallet.domain.repository.RecurrentRepository
import javax.inject.Inject

class RecurrentRepositoryImpl @Inject constructor(
    private val dao: RecurrentDao
) : RecurrentRepository {

    override suspend fun getPendingRecurrent() = dao.getPendingRecurrent()
    override suspend fun updateAndCreatePendingRecurrent(
        newPending: List<Expense>,
        oldPending: List<Expense>
    ) = dao.updateAndCreatePendingRecurrent(newPending, oldPending)

}