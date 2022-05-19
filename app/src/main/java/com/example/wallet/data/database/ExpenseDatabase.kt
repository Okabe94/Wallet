package com.example.wallet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallet.data.dao.ExpenseDao
import com.example.wallet.data.dao.RecurrentDao
import com.example.wallet.data.entity.Expense


@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun recurrentDao(): RecurrentDao
}