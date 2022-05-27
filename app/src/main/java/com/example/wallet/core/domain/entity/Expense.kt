package com.example.wallet.core.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    val name: String,
    val amount: String,
    val isMonthly: Boolean,
    val months: Long = 0,
    val createdAt: Long,
    var updatedUntil: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
