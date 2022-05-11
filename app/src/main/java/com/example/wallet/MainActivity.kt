package com.example.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.wallet.data.viewmodel.AddExpenseViewModel
import com.example.wallet.ui.component.WalletBase
import com.example.wallet.ui.navigation.SetupNavGraph
import com.example.wallet.ui.screen.AddExpenseScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: AddExpenseViewModel by viewModels()
            val navController = rememberNavController()
            SetupNavGraph(navController)
            WalletBase { AddExpenseScreen(navController, viewModel) }
        }
    }
}

