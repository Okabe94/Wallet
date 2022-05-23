package com.example.wallet.presentation.view.home

import androidx.lifecycle.ViewModel
import com.example.wallet.data.repository.ExpenseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ExpenseRepositoryImpl
) : ViewModel() {
    val expenses = repository.getExpenses()
}