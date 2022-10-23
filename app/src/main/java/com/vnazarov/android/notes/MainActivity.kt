package com.vnazarov.android.notes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vnazarov.android.notes.navigation.NotesNavHost
import com.vnazarov.android.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                val context = LocalContext.current
                val mainViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Notes App")
                        }, backgroundColor = Color.Blue,
                        contentColor = Color.White,
                        elevation = 12.dp
                    )
                }, content = {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NotesNavHost(mainViewModel)
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