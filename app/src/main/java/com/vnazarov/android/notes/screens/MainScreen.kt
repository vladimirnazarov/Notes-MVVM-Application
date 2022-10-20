package com.vnazarov.android.notes.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vnazarov.android.notes.MainViewModel
import com.vnazarov.android.notes.MainViewModelFactory
import com.vnazarov.android.notes.model.Note
import com.vnazarov.android.notes.navigation.NavRoute
import com.vnazarov.android.notes.ui.theme.NotesTheme

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mainViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    val notes = mainViewModel.readTest.observeAsState(listOf()).value

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(NavRoute.Add.route) }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Icons",
                tint = Color.White
            )
        }
    }) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            LazyColumn {
                items(notes){ note ->
                    NoteItem(note = note, navController = navController)
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable { navController.navigate(NavRoute.Note.route) },
        elevation = 6.dp
    )
    {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = note.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = note.subtitle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevMainScreen() {
    NotesTheme {
        MainScreen(navController = rememberNavController())
    }
}