package com.example.wallet.feature_home.presentation.view.home

import androidx.lifecycle.ViewModel
import com.example.wallet.feature_add_expense.data.repository.CreateExpenseRepositoryImpl
import com.example.wallet.feature_home.domain.repository.HomeExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeExpenseRepository
) : ViewModel() {
    val expenses = repository.getExpenses()
}