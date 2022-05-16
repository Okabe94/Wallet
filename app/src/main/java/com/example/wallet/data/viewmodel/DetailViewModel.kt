package com.example.wallet.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wallet.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    fun getExpense(id: Int) = expenseRepository.getExpense(id)
}
