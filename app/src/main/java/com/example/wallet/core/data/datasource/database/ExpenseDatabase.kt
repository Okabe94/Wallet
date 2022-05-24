package com.example.wallet.core.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallet.feature_add_expense.data.datasource.dao.ExpenseDao
import com.example.wallet.feature_recurrent.data.datasource.dao.RecurrentDao
import com.example.wallet.core.domain.entity.Expense


@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun recurrentDao(): RecurrentDao
}