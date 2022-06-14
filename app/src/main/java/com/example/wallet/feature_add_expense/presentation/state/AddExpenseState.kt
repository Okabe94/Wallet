package com.example.wallet.feature_add_expense.presentation.state

data class AddExpenseState(
    val name: String = "",
    val amount: String = "",
    val isMonthly: Boolean = false
)

