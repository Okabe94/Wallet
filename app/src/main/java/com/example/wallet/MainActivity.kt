package com.example.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.wallet.core.presentation.component.AppBaseSurface
import com.example.wallet.feature_recurrent.presentation.view.MainScreen
import com.example.wallet.feature_recurrent.presentation.view.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.checkForRecurring()
        setContent {
            AppBaseSurface { MainScreen() }
        }
    }
}

