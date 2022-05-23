package com.example.wallet.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.wallet.core.presentation.component.AppBaseSurface
import com.example.wallet.presentation.view.main.MainScreen
import com.example.wallet.presentation.view.main.MainViewModel
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

