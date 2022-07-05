package com.example.wallet.feature_main.data.repository

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_main.domain.repository.RecurrentRepository

class FakeRecurrentRepository(private val list: List<Expense> = emptyList()) : RecurrentRepository {

    var updatedList = emptyList<Expense>()

    override suspend fun updateRecurrent(list: List<Expense>) {
        updatedList = list.sortedBy { it.id }
    }

    override suspend fun getPendingRecurrent(today: Long) = list.filter {
        it.updatedUntil < today && it.isMonthly
    }
}