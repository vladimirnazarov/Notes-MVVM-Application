package com.vnazarov.android.notes.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.vnazarov.android.notes.utils.Constants
import com.vnazarov.android.notes.utils.Constants.Keys.DELETE
import com.vnazarov.android.notes.utils.Constants.Keys.EDIT_NOTE
import com.vnazarov.android.notes.utils.Constants.Keys.EMPTY
import com.vnazarov.android.notes.utils.Constants.Keys.NAV_BACK
import com.vnazarov.android.notes.utils.Constants.Keys.NONE
import com.vnazarov.android.notes.utils.Constants.Keys.SUBTITLE
import com.vnazarov.android.notes.utils.Constants.Keys.TITLE
import com.vnazarov.android.notes.utils.Constants.Keys.UPDATE
import com.vnazarov.android.notes.utils.Constants.Keys.UPDATE_NOTE
import com.vnazarov.android.notes.utils.DB_TYPE
import com.vnazarov.android.notes.utils.TYPE_FIREBASE
import com.vnazarov.android.notes.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteID: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = when(DB_TYPE){
        TYPE_FIREBASE -> {
            notes.firstOrNull { it.firebaseID == noteID } ?: Note()
        }
        TYPE_ROOM -> {
            notes.firstOrNull { it.id == noteID?.toInt() } ?: Note()
        }
        else -> Note()
    }

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(EMPTY) }
    var subtitle by remember { mutableStateOf(EMPTY) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = EDIT_NOTE,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = TITLE) },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text(text = SUBTITLE) },
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(
                                note = Note(
                                    id = note.id,
                                    title = title,
                                    subtitle = subtitle,
                                    firebaseID = note.firebaseID
                                )
                            ) {
                                navController.navigate(NavRoute.Main.route)
                            }
                        }
                    ) {
                        Text(text = UPDATE_NOTE)
                    }
                }
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = note.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 32.dp),
                        )
                        Text(
                            text = note.subtitle,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        coroutineScope.launch {
                            title = note.title
                            subtitle = note.subtitle
                            bottomSheetState.show()
                        }
                    }) {
                        Text(text = UPDATE)
                    }
                    Button(onClick = {
                        viewModel.deleteNote(note = note){
                            navController.navigate(NavRoute.Main.route)
                        }
                    }) {
                        Text(text = DELETE)
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    onClick = { navController.navigate(NavRoute.Main.route) }) {
                    Text(text = NAV_BACK)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevNoteScreen() {
    NotesTheme {
        val context = LocalContext.current
        val mainViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mainViewModel,
            noteID = "1"
        )
    }
}