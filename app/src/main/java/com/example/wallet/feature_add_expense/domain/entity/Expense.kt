package com.example.wallet.feature_add_expense.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Expense(
    val name: String,
    val amount: String,
    val isMonthly: Boolean,
    val date: Long = Calendar.getInstance().timeInMillis,
    val recurrentUpdated: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
