package com.example.wallet.feature_recurrent.domain.repository

import com.example.wallet.core.domain.entity.Expense

interface RecurrentRepository {
    suspend fun updateRecurrent(list: List<Expense>)
    suspend fun getPendingRecurrent(today: Long): List<Expense>
}