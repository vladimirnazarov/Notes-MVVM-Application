package com.vnazarov.android.notes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.vnazarov.android.notes.navigation.NavRoute
import com.vnazarov.android.notes.navigation.NotesNavHost
import com.vnazarov.android.notes.ui.theme.NotesTheme
import com.vnazarov.android.notes.utils.DB_TYPE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val context = LocalContext.current
                val mainViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                val navController = rememberNavController()

                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Notes App")
                                if (DB_TYPE.value.isNotEmpty()) {
                                    Icon(
                                        imageVector = Icons.Default.ExitToApp,
                                        contentDescription = "",
                                        modifier = Modifier.clickable {
                                            mainViewModel.signOut {
                                                navController.navigate(NavRoute.Start.route) {
                                                    popUpTo(NavRoute.Start.route) {
                                                        inclusive = true
                                                    }
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }, backgroundColor = Color.Blue,
                        contentColor = Color.White,
                        elevation = 12.dp
                    )
                }, content = {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NotesNavHost(mainViewModel, navController)
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesTheme {
    }
}