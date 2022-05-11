package com.example.wallet.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.wallet.data.viewmodel.AddExpenseViewModel
import com.example.wallet.ui.component.WalletBase
import com.example.wallet.ui.component.reusable.CurrencyTransformation
import com.example.wallet.ui.component.reusable.WalletInputText

@Composable
@Preview(name = "Light mode", showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
private fun AddExpenseScreenPreview() = WalletBase { AddExpenseScreen() }

@Composable
fun AddExpenseScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddExpenseViewModel = hiltViewModel()
): Unit = with(viewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
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
            onChange = ::onNameChange
        )
        WalletInputText(
            name = stringResource(R.string.amount),
            value = amount,
            options = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = Icons.Default.AccountCircle,
            visualTransformation = CurrencyTransformation(),
            onChange = ::onAmountChange
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.monthly))
            Checkbox(
                checked = isMonthly,
                onCheckedChange = ::onMonthlyChange
            )
        }
        Button(onClick = { createNewExpense(navController) }) {
            Text(text = stringResource(R.string.create))
        }
    }
}