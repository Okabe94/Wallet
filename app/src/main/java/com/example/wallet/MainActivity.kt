package com.example.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.wallet.ui.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val viewModel: AddExpenseViewModel by viewModels()
            val navController = rememberNavController()
            SetupNavGraph(navController)
//            WalletBase { AddExpenseScreen(navController, viewModel) }
        }
    }
}

