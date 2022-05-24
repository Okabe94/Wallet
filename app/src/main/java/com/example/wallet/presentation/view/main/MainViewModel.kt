package com.example.wallet.presentation.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.preferences.application.ApplicationPreferences
import com.example.wallet.data.util.dispatcher.ApplicationDispatcher
import com.example.wallet.domain.usecase.recurrent.RecurrentUseCases
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
            recurrentUseCases.checkRecurrent(this)
        }
    }

}
