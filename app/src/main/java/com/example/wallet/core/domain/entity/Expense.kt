package com.example.wallet.core.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallet.feature_recurrent.domain.model.time.WalletTime

@Entity
data class Expense(
    val name: String,
    val amount: String,
    val isMonthly: Boolean,
    val months: Long = 0,
    val createdAt: Long = WalletTime.create().getTime(),
    var updatedUntil: Long = WalletTime.create().nextMonth().getTime(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
