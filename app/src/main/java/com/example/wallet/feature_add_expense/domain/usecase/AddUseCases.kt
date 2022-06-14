package com.example.wallet.feature_add_expense.domain.usecase

import javax.inject.Inject

data class AddUseCases @Inject constructor(
    val addExpense: AddExpenseUseCase
)