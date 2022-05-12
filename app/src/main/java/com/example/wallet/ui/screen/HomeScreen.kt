package com.example.wallet.ui.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallet.data.viewmodel.HomeViewModel
import com.example.wallet.ui.component.wallet.WalletBase

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun HomeScreenPreview() = WalletBase { HomeScreen() }

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel(),
    paddingValues: PaddingValues? = null
) {
    val expenses = viewModel.expenses.collectAsState(initial = listOf())

    WalletBase {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues ?: PaddingValues(10.dp))
        ) {
            items(expenses.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = it.name, style = MaterialTheme.typography.body1)
                        Text(text = it.amount, style = MaterialTheme.typography.body2)
                    }

                    if (it.isMonthly) {
                        Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }
        }
    }
}

