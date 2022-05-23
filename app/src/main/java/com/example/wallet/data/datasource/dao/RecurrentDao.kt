package com.example.wallet.data.datasource.dao

import androidx.room.*
import com.example.wallet.data.datasource.entity.Expense

@Dao
interface RecurrentDao {

    @Query("SELECT * From Expense WHERE isMonthly IS 1 AND recurrentUpdated IS 0")
    suspend fun getPendingRecurrent(): List<Expense>

    @Transaction
    suspend fun updateAndCreatePendingRecurrent(
        newPending: List<Expense>,
        oldPending: List<Expense>
    ) {
        createAll(newPending)
        updateAll(oldPending)
    }

    @Insert
    suspend fun createAll(expense: List<Expense>)

    @Update
    suspend fun updateAll(expense: List<Expense>)
}