package com.example.wallet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.wallet.data.viewmodel.MainViewModel
import com.example.wallet.ui.component.base.AppBaseSurface
import com.example.wallet.ui.screen.MainScreen
import com.example.wallet.ui.util.WindowType
import com.example.wallet.ui.util.rememberWindowInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.CoroutineContext

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

