package com.example.wallet.feature_home.presentation.view.detail

import androidx.lifecycle.ViewModel
import com.example.wallet.feature_home.domain.repository.HomeExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val homeExpenseRepository: HomeExpenseRepository
) : ViewModel() {

    fun getExpense(id: Int) = homeExpenseRepository.getExpense(id)
}
