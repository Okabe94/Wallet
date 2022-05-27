package com.example.wallet.feature_main.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.core.presentation.util.dispatcher.ApplicationDispatcher
import com.example.wallet.feature_main.domain.usecase.RecurrentUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcher: ApplicationDispatcher,
    private val recurrentUseCases: RecurrentUseCases
) : ViewModel() {

    fun checkForRecurring() {
        viewModelScope.launch(dispatcher.io) {
            with(recurrentUseCases) {
                getRecurrent()?.let { updateRecurrent(this@launch, it) }
            }
        }
    }
}
