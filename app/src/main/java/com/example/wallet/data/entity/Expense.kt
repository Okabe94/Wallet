package com.example.wallet.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    val name: String,
    val amount: String,
    val isMonthly: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
