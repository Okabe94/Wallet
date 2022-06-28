package com.example.wallet.feature_add_expense.domain.usecase

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository
import com.example.wallet.feature_add_expense.presentation.state.AddExpenseState
import com.example.wallet.feature_main.domain.time.TimeProvider
import javax.inject.Inject

data class AddExpenseUseCase @Inject constructor(
    private val createExpenseRepository: CreateExpenseRepository,
    private val timeProvider: TimeProvider
) {
    suspend operator fun invoke(state: AddExpenseState): Boolean = with(state) {
        return try {
            val now = timeProvider.now()
            val today = now.millis()
            val until = now.rollMonths().millis()
            createExpenseRepository.create(Expense(name, amount, isMonthly, today, until))
            true
        } catch (e: Exception) {
            false
        }
    }
}