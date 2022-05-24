package com.example.wallet.feature_recurrent.domain.repository

import com.example.wallet.feature_add_expense.domain.entity.Expense

interface RecurrentRepository {
    suspend fun getPendingRecurrent() : List<Expense>
    suspend fun updateAndCreatePendingRecurrent(
        newPending: List<Expense>,
        oldPending: List<Expense>
    )
}