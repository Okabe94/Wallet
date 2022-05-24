package com.example.wallet.feature_recurrent.data.repository

import com.example.wallet.feature_recurrent.data.datasource.dao.RecurrentDao
import com.example.wallet.feature_add_expense.domain.entity.Expense
import com.example.wallet.feature_recurrent.domain.repository.RecurrentRepository
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