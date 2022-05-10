package com.example.wallet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallet.data.viewmodel.AddExpenseViewModel
import com.example.wallet.ui.component.reusable.MainScaffold
import com.example.wallet.ui.component.WalletBase
import com.example.wallet.ui.model.BottomBarActionButton
import com.example.wallet.ui.model.BottomBarMenuItem
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
//                    MainScaffoldSection {
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
//                        ExpenseListScreen(it, expenses.value)
                }
            }
        }
    }
}
//}

@Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScaffoldSectionPreview() = WalletBase {
    MainScaffold(
        floating = BottomBarActionButton(
            Icons.Default.Add,
            CircleShape,
            stringResource(R.string.add)
        ) {},
        menuItems = listOf(BottomBarMenuItem(
            stringResource(R.string.home),
            Icons.Default.Home,
            false
        ) {}, BottomBarMenuItem(
            stringResource(R.string.user),
            Icons.Default.Person,
            false
        ) {}
        )
    )
}

