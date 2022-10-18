package com.vnazarov.android.notes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vnazarov.android.notes.navigation.NavRoute
import com.vnazarov.android.notes.ui.theme.NotesTheme

@Composable
fun MainScreen(navController: NavHostController) {
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
            NoteItem(
                title = "Note 1",
                subtitle = "Subtitle for note 1",
                navController = navController
            )
            NoteItem(
                title = "Note 2",
                subtitle = "Subtitle for note 2",
                navController = navController
            )
            NoteItem(
                title = "Note 3",
                subtitle = "Subtitle for note 3",
                navController = navController
            )
            NoteItem(
                title = "Note 4",
                subtitle = "Subtitle for note 4",
                navController = navController
            )
//            NoteItem(
//                title = "Note 5",
//                subtitle = "Subtitle for note 5",
//                navController = navController
//            )
//            NoteItem(
//                title = "Note 6",
//                subtitle = "Subtitle for note 6",
//                navController = navController
//            )
//            NoteItem(
//                title = "Note 7",
//                subtitle = "Subtitle for note 7",
//                navController = navController
//            )
//            NoteItem(
//                title = "Note 8",
//                subtitle = "Subtitle for note 8",
//                navController = navController
//            )
//            NoteItem(
//                title = "Note 9",
//                subtitle = "Subtitle for note 9",
//                navController = navController
//            )
//            NoteItem(
//                title = "Note 10",
//                subtitle = "Subtitle for note 10",
//                navController = navController
//            )

        }
    }
}

@Composable
fun NoteItem(title: String, subtitle: String, navController: NavHostController) {
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
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = subtitle)
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