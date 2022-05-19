package com.example.wallet.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.wallet.data.entity.Expense

@Dao
interface RecurrentDao {
    @Query("SELECT * From Expense WHERE isMonthly is 1")
    suspend fun getLatestRecurrent() : List<Expense>

    @Query("SELECT * From Expense WHERE isMonthly IS 1 AND date > :lastRequestDate")
    suspend fun getLatestRecurrent(lastRequestDate: Long) : List<Expense>
}