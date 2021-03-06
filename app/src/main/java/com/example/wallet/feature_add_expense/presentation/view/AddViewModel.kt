package com.example.wallet.feature_add_expense.presentation.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.core.presentation.util.dispatcher.ApplicationDispatcher
import com.example.wallet.feature_add_expense.domain.usecase.AddUseCases
import com.example.wallet.feature_add_expense.presentation.state.AddExpenseEvent
import com.example.wallet.feature_add_expense.presentation.state.AddExpenseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val useCases: AddUseCases,
    private val dispatcher: ApplicationDispatcher
) : ViewModel() {

    private val _state = mutableStateOf(AddExpenseState())
    val state: State<AddExpenseState> = _state

    fun onEvent(event: AddExpenseEvent) {
        when (event) {
            is AddExpenseEvent.AmountChange -> _state.value =
                _state.value.copy(amount = event.amount)
            is AddExpenseEvent.MonthlyChange -> _state.value =
                _state.value.copy(isMonthly = event.monthly)
            is AddExpenseEvent.NameChange ->
                _state.value = _state.value.copy(name = event.name)
            AddExpenseEvent.Save -> viewModelScope.launch(dispatcher.IO) {
                useCases.addExpense(_state.value)
            }
        }
    }
}