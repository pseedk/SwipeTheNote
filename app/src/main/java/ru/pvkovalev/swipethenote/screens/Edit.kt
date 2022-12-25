package ru.pvkovalev.swipethenote.screens

import android.annotation.SuppressLint
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
import kotlinx.coroutines.launch
import ru.pvkovalev.swipethenote.MainViewModel
import ru.pvkovalev.swipethenote.MainViewModelFactory
import ru.pvkovalev.swipethenote.model.Note
import ru.pvkovalev.swipethenote.navigation.NavRoute
import ru.pvkovalev.swipethenote.ui.theme.SwipeTheNoteTheme
import ru.pvkovalev.swipethenote.utils.Constants.Keys.DELETE
import ru.pvkovalev.swipethenote.utils.Constants.Keys.DESCRIPTION
import ru.pvkovalev.swipethenote.utils.Constants.Keys.EDIT_NOTE
import ru.pvkovalev.swipethenote.utils.Constants.Keys.EMPTY
import ru.pvkovalev.swipethenote.utils.Constants.Keys.NAV_BACK
import ru.pvkovalev.swipethenote.utils.Constants.Keys.NONE
import ru.pvkovalev.swipethenote.utils.Constants.Keys.UPDATE
import ru.pvkovalev.swipethenote.utils.Constants.Keys.UPDATE_NOTE

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull { it.id == noteId?.toInt() } ?: Note(description = NONE)
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var description by remember { mutableStateOf(EMPTY) }

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
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = DESCRIPTION) },
                        isError = description.isEmpty()
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(
                                note = Note(id = note.id, description = description)
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
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
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
                            text = note.description
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                description = note.description
                                bottomSheetState.show()
                            }
                        }) {
                        Text(text = UPDATE)
                    }

                    Button(
                        onClick = {
                            viewModel.deleteNote(note = note) {
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
                    onClick = {
                        navController.navigate(NavRoute.Main.route)
                    }) {
                    Text(text = NAV_BACK)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditScreen() {
    SwipeTheNoteTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        EditScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}