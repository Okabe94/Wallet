package com.example.wallet.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallet.data.viewmodel.AddExpenseViewModel
import com.example.wallet.ui.component.WalletBase

@Composable
@Preview(name = "Light mode", showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
private fun AddExpenseScreenPreview() = WalletBase { AddExpenseScreen() }

@Composable
fun AddExpenseScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddExpenseViewModel = hiltViewModel()
//    name: String = "",
//    onNameChange: (String) -> Unit = {},
//    amount: String = "",
//    onAmountChange: (String) -> Unit = {},
//    isMonthly: Boolean = false,
//    onCheckedChange: (Boolean) -> Unit = {},
//    onActionClicked: () -> Unit = {}
) {
    WalletBase {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 30.dp),
                text = "Nueva Entrada",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.SemiBold)
            )
//        OutlinedTextField(
//            value = name,
//            onValueChange = onNameChange,
//            placeholder = { Text(text = "Nombre") },
//            label = { Text(text = "Nombre") })
//        OutlinedTextField(
//            value = amount,
//            onValueChange = onAmountChange,
//            placeholder = { Text(text = "Cantidad") },
//            label = { Text(text = "Cantidad") })
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "Mensual")
//            Checkbox(checked = isMonthly, onCheckedChange = onCheckedChange)
//        }
//        Button(onClick = onActionClicked) {
//            Text(text = "Crear")
//        }
        }
    }
}