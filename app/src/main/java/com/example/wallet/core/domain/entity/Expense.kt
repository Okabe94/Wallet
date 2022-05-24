package com.example.wallet.core.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallet.feature_recurrent.domain.util.nextMonth
import com.example.wallet.feature_recurrent.domain.util.requiredTime
import com.example.wallet.feature_recurrent.domain.util.standardTime

@Entity
data class Expense(
    val name: String,
    val amount: String,
    val isMonthly: Boolean,
    val months: Long = 0,
    val createdAt: Long = standardTime().requiredTime(),
    val updatedUntil: Long = standardTime().nextMonth().requiredTime(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) {
}
