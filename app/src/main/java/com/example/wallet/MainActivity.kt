package com.example.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.wallet.data.viewmodel.MainViewModel
import com.example.wallet.presentation.component.base.AppBaseSurface
import com.example.wallet.presentation.screen.MainScreen
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

