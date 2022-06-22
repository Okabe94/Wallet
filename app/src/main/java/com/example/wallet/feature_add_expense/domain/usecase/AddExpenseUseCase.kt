package com.example.wallet.feature_add_expense.domain.usecase

import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.feature_add_expense.domain.repository.CreateExpenseRepository
import com.example.wallet.feature_add_expense.presentation.state.AddExpenseState
import com.example.wallet.feature_main.data.time.Time
import javax.inject.Inject

data class AddExpenseUseCase @Inject constructor(
    private val createExpenseRepository: CreateExpenseRepository,
    private val timeManager: Time
) {
    suspend operator fun invoke(state: AddExpenseState): Boolean = with(state) {
        return try {
            val today = timeManager.now().getTime()
            val next = timeManager.nextMonth().getTime()
            // Validate and send response
            createExpenseRepository.create(Expense(name, amount, isMonthly, today, next))
            true
        } catch (e: Exception) {
            false
        }
    }
}