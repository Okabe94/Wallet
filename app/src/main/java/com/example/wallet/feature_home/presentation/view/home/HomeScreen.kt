package com.example.wallet.feature_home.presentation.view.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallet.R
import com.example.wallet.core.domain.entity.Expense
import com.example.wallet.core.presentation.component.AppBaseSurface
import com.example.wallet.core.presentation.util.navigation.Screen

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun HomeScreenPreview() = AppBaseSurface { HomeScreen(PaddingValues(10.dp)) }

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
private fun ExpenseItemPreview() =
    AppBaseSurface { ExpenseItem(Expense("Comida", "$ 2,000", true)) {} }

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val expenses = viewModel.expenses.collectAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(expenses.value) {
            ExpenseItem(it) { id ->
                navController.navigate("${Screen.DetailScreen.route}/$id")
            }
        }
    }
}

@Composable
private fun ExpenseItem(it: Expense, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(it.id) },
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp), text = it.id.toString()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = it.amount)
                if (it.isMonthly) Text(
                    text = stringResource(id = R.string.monthly),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
