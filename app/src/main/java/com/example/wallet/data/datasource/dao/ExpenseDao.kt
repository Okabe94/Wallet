package com.example.wallet.data.datasource.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.wallet.data.datasource.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = IGNORE)
    suspend fun createExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("SELECT * FROM Expense")
    fun getExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM Expense WHERE id LIKE :id LIMIT 1")
    fun getExpense(id: Int): Flow<Expense>
}