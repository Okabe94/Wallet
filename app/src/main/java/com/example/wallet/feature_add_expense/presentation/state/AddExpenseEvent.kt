package com.example.wallet.feature_add_expense.presentation.state

sealed class AddExpenseEvent {
    object Save
    data class NameChange(val name: String) : AddExpenseEvent()
    data class AmountChange(val amount: String) : AddExpenseEvent()
    data class MonthlyChange(val monthly: Boolean) : AddExpenseEvent()
}
