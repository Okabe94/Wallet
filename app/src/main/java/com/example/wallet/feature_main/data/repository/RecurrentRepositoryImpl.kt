package com.example.wallet.feature_main.data.repository

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.data.datasource.dao.RecurrentDao
import com.example.wallet.feature_main.domain.repository.RecurrentRepository
import javax.inject.Inject

class RecurrentRepositoryImpl @Inject constructor(
    private val dao: RecurrentDao
) : RecurrentRepository {
    override suspend fun updateRecurrent(list: List<Expense>) = dao.updateAll(list)
    override suspend fun getPendingRecurrent(today: Long) = dao.getPendingRecurrent(today)
}