package com.example.wallet.feature_add_expense.presentation.view

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallet.R
import com.example.wallet.core.presentation.component.AppBaseSurface
import com.example.wallet.core.presentation.component.WalletInputText
import com.example.wallet.feature_add_expense.presentation.state.AddExpenseEvent

@Composable
@Preview(name = "Light mode", showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
private fun AddScreenPreview() = AppBaseSurface { AddScreen(PaddingValues(10.dp)) }

@Composable
fun AddScreen(
    paddingValues: PaddingValues,
    navController: NavController = rememberNavController(),
    viewModel: AddViewModel = hiltViewModel()
): Unit = with(viewModel.state.value) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(R.string.new_entry),
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold)
        )
        WalletInputText(
            name = stringResource(R.string.name),
            value = name,
            options = KeyboardOptions(
                imeAction = ImeAction.Next,
                autoCorrect = true
            ),
            onChange = { viewModel.onEvent(AddExpenseEvent.NameChange(it)) }

        )
        WalletInputText(
            name = stringResource(R.string.amount),
            value = amount,
            options = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = Icons.Default.AccountCircle,
//            visualTransformation = CurrencyTransformation(),
            onChange = { viewModel.onEvent(AddExpenseEvent.AmountChange(it)) }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.monthly))
            Checkbox(
                checked = isMonthly,
                onCheckedChange = { viewModel.onEvent(AddExpenseEvent.MonthlyChange(it)) }
            )
        }
        Button(onClick = { viewModel.onEvent(AddExpenseEvent.Save) }) {
            Text(text = stringResource(R.string.create))
        }
    }
}