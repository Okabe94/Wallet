package com.example.wallet.presentation.view.expense.detail

import androidx.lifecycle.ViewModel
import com.example.wallet.data.repository.ExpenseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepositoryImpl
) : ViewModel() {

    fun getExpense(id: Int) = expenseRepository.getExpense(id)
}