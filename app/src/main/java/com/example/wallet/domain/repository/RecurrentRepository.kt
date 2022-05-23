package com.example.wallet.domain.repository

import com.example.wallet.data.datasource.entity.Expense

interface RecurrentRepository {
    suspend fun getPendingRecurrent() : List<Expense>
    suspend fun updateAndCreatePendingRecurrent(
        newPending: List<Expense>,
        oldPending: List<Expense>
    )
}