package com.example.wallet.feature_add_expense.presentation.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.core.presentation.util.dispatcher.ApplicationDispatcher
import com.example.wallet.feature_add_expense.data.repository.ExpenseRepositoryImpl
import com.example.wallet.feature_add_expense.domain.usecase.AddUseCases
import com.example.wallet.feature_main.domain.model.time.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val useCases: AddUseCases,
    private val dispatcher: ApplicationDispatcher
) : ViewModel() {

    private var _name by mutableStateOf("")
    var name = _name

    private var _amount by mutableStateOf("")
    var amount = _amount

    private var _isMonthly by mutableStateOf(false)
    var isMonthly = _isMonthly

    private fun clearFields() {
        _name = ""
        _amount = ""
        _isMonthly = false
    }

    fun onNameChange(text: String) {
        name = text
    }

    fun onAmountChange(text: String) {
        amount = text
    }

    fun onMonthlyChange(checked: Boolean) {
        isMonthly = checked
    }

    fun createNewExpense(navController: NavController) {
        viewModelScope.launch(dispatcher.io) {
            val isValid = useCases.addExpense(name, amount, isMonthly)
            if (isValid) {
                clearFields()
                navController.navigateUp()
            }
        }
    }
}