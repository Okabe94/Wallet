package com.example.wallet.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wallet.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    val expenses = repository.getExpenses()
//        .map {
//        it.map { item -> item.copy(amount = "$ 2,400") }
//    }
}