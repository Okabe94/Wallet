package com.example.wallet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallet.data.viewmodel.AddExpenseViewModel
import com.example.wallet.ui.screen.AddExpenseScreen
import com.example.wallet.ui.screen.ExpenseListScreen
import com.example.wallet.ui.theme.WalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AddExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalletTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScaffoldSection {
//                        with(viewModel) {
//                            AddExpenseScreen(
//                                expenseName,
//                                ::onExpenseNameChange,
//                                expenseAmount,
//                                ::onExpenseAmountChange,
//                                isMonthly,
//                                ::onMonthlyChange,
//                                ::createNewExpense
//                            )
//                        }
                        val expenses = viewModel.expenses.collectAsState(emptyList())
                        ExpenseListScreen(it, expenses.value)
                    }
                }
            }
        }
    }
}

@Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScaffoldSectionPreview() {
    WalletTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScaffoldSection()
        }
    }
}

@Composable
fun MainScaffoldSection(content: @Composable (PaddingValues) -> Unit = {}) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colors.surface,
                cutoutShape = CircleShape,
                contentColor = MaterialTheme.colors.onSurface,
                contentPadding = PaddingValues(10.dp),
                elevation = 5.dp
            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    selected = true, onClick = { /*TODO*/ }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Filled.Person,
                            contentDescription = null
                        )
                    },
                    selected = false, onClick = { /*TODO*/ }
                )
            }
        },
        content = content
    )
}
