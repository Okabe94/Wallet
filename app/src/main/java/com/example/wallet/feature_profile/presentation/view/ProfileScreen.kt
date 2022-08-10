package com.example.wallet.feature_profile.presentation.view

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wallet.core.presentation.component.AppBaseSurface

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = hiltViewModel()
) = AppBaseSurface {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hola desde perfíl")
        Text(text = "Hola desde perfíl")
        Text(text = "Hola desde perfíl")
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    AppBaseSurface {
        ProfileScreen(paddingValues = PaddingValues(0.dp))
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun ProfileScreenPreviewDark() {
    AppBaseSurface {
        ProfileScreen(paddingValues = PaddingValues(0.dp))
    }
}

