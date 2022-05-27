package com.example.wallet.feature_add_expense.domain.usecase

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.ExpenseRepository
import com.example.wallet.feature_main.domain.model.time.Time

data class AddExpenseUseCase(
    private val expenseRepository: ExpenseRepository,
    private val timeManager: Time
) {
    suspend operator fun invoke(
        name: String, amount: String, isMonthly: Boolean
    ): Boolean = with(timeManager) {
        val today = now().getTime()
        val next = nextMonth().getTime()
        // Validate and send response
        expenseRepository.createExpense(Expense(name, amount, isMonthly, today, next))
        return true
    }
}