package com.example.wallet.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.wallet.data.entity.Expense

@Dao
interface RecurrentDao {

    @Query("SELECT * From Expense WHERE isMonthly IS 1 AND recurrentUpdated IS 0")
    suspend fun getPendingRecurrent(): List<Expense>

    @Transaction
    suspend fun createRecurrentExpenses(list: List<Expense>) = list.forEach { createExpense(it) }

    @Insert(onConflict = ABORT)
    suspend fun createExpense(expense: Expense)

}