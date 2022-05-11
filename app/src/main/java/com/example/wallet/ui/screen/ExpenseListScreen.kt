package com.example.wallet.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallet.data.viewmodel.ExpenseListViewModel
import com.example.wallet.ui.component.wallet.MainScaffold
import com.example.wallet.ui.component.wallet.WalletBase

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun ExpenseListScreenPreview() = WalletBase { ExpenseListScreen() }

@Composable
fun ExpenseListScreen(
    navController: NavController = rememberNavController(),
    viewModel: ExpenseListViewModel = hiltViewModel()
) {
    WalletBase {
        MainScaffold(floating =, menuItems =) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
//            .padding(paddingValues)
            ) {
//        items(expenses) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(20.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Column {
//                    Text(text = it.name, style = MaterialTheme.typography.body1)
//                    Text(text = it.amount, style = MaterialTheme.typography.body2)
//                }
//
//                if (it.isMonthly) {
//                    Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
//                }
//            }
//            Divider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 10.dp),
//                color = Color.LightGray,
//                thickness = 1.dp
//            )
//        }
            }
        }
    }
}
