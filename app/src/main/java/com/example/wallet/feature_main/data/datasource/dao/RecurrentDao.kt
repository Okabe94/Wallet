package com.example.wallet.feature_main.data.datasource.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.wallet.core.domain.entity.Expense

@Dao
interface RecurrentDao {
    @Query("SELECT * From Expense WHERE isMonthly IS 1 AND updatedUntil < :today")
    suspend fun getPendingRecurrent(today: Long): List<Expense>

    @Update
    suspend fun updateAll(expense: List<Expense>)
}