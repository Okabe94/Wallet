package com.example.wallet.feature_add_expense.presentation.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.wallet.core.presentation.util.dispatcher.ApplicationDispatcher
import com.example.wallet.feature_add_expense.domain.usecase.AddUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val useCases: AddUseCases,
    private val dispatcher: ApplicationDispatcher
) : ViewModel() {

    private val _name = mutableStateOf("")
    var name = _name.value

    private var _amount by mutableStateOf("")
    var amount = _amount

    private var _isMonthly by mutableStateOf(false)
    var isMonthly = _isMonthly

    private fun clearFields() {
        _name.value = ""
        _amount = ""
        _isMonthly = false
    }

    fun onNameChange(text: String) {
        _name.value = text
    }

    fun onAmountChange(text: String) {
        _amount = text
    }

    fun onMonthlyChange(checked: Boolean) {
        _isMonthly = checked
    }

    fun createNewExpense(navController: NavController) {
        viewModelScope.launch(dispatcher.io) {
            val isValid = useCases.addExpense(_name.value, _amount, _isMonthly)
            if (isValid) {
                clearFields()
                navController.navigateUp()
            }
        }
    }
}