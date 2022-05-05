package com.example.wallet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.wallet.data.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = IGNORE)
    suspend fun createExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM Expense")
    fun getExpenses() : Flow<List<Expense>>

    @Query("SELECT * FROM Expense WHERE id LIKE :id LIMIT 1")
    fun getExpense(id: Int) : Flow<Expense>
}