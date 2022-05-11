package com.example.wallet.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wallet.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpenseListViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    val expenses = repository.getExpenses()

}