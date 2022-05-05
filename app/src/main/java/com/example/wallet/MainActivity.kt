package com.example.wallet

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.wallet.data.entity.Expense
import com.example.wallet.data.viewmodel.ExpenseViewModel
import com.example.wallet.ui.theme.WalletTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: ExpenseViewModel by viewModels()

        viewModel.createExpense(Expense("Hola", "234", true))

        setContent {
            WalletTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    a()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true, name = "Light mode")
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "Dark mode")
@Composable
fun DefaultPreview() {
    WalletTheme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Android")
        }
    }
}

@Preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun a() {
    WalletTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(modifier = Modifier.fillMaxSize(),
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
                                         imageVector = Icons.Default.Email,
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
                     }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = 5.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Monthly expenses")
                            Text(text = "$2,400")
                        }
                    }
                }
            }
        }
    }
}
