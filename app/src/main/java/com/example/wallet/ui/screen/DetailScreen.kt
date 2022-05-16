package com.example.wallet.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallet.data.viewmodel.DetailViewModel

@Composable
@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DetailScreenPreview() {

}

@Composable
fun DetailScreen(
    paddingValues: PaddingValues,
    navController: NavController = rememberNavController(),
    viewModel: DetailViewModel = hiltViewModel(),
    id: Int?
) {
    if (id == null) {
        Text(text = "ERROR")
        return
    }
    val expense = viewModel.getExpense(id).collectAsState(initial = null)
    Text(text = "$expense")

}
