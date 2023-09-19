package com.example.tasksmaster.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.ui.theme.Pink80
import com.example.tasksmaster.ui.theme.Purple80
import com.example.tasksmaster.ui.theme.PurpleGrey80
import com.example.tasksmaster.view.MainViewModel
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenSetup(
    viewModel: MainViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onClick: (Task) -> Unit
    ) {

    val allTasks by viewModel.allTasks.observeAsState(listOf())
    
    ModalNavigationDrawer(
        modifier = Modifier
            .background(PurpleGrey80),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = PurpleGrey80,
                content = {
                    var checked by remember {
                        mutableStateOf(true)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.settings),
                            fontSize = 18.sp
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.theme),
                                fontSize = 18.sp
                            )

                            Switch(
                                checked = checked,
                                onCheckedChange = {
                                    checked = it
                                },
                                colors = SwitchDefaults.colors(
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Purple80
                                ),
                                thumbContent = {
                                    if (checked) {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.outline_dark_mode_24),
                                            contentDescription = null,
                                            modifier = Modifier.size(SwitchDefaults.IconSize)
                                        )
                                    } else {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.baseline_light_mode_24),
                                            contentDescription = null,
                                            modifier = Modifier.size(SwitchDefaults.IconSize),
                                            tint = Pink80
                                        )
                                    }
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.language),
                                fontSize = 18.sp
                            )

                            var expanded by remember { mutableStateOf(false) }
                            var selectedText by remember { mutableIntStateOf(R.string.russian) }

                            val icon = if (expanded)
                                Icons.Filled.KeyboardArrowUp
                            else
                                Icons.Filled.KeyboardArrowDown
                            Row(
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    TextButton(
                                        onClick = { expanded = !expanded }
                                    ) {
                                        Text(
                                            text = stringResource(id = selectedText),
                                            fontSize = 16.sp
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false },
                                    ) {
                                        DropdownMenuItem(
                                            text = {
                                                Text(text = stringResource(id = R.string.russian))
                                            },
                                            onClick = {
                                                selectedText = R.string.russian
                                                expanded = !expanded
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = {
                                                Text(text = stringResource(id = R.string.english))
                                            },
                                            onClick = {
                                                selectedText = R.string.english
                                                expanded = !expanded
                                            }
                                        )
                                    }
                                }
                                IconButton(
                                    onClick = { expanded = !expanded }
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = "arrow"
                                    )
                                }
                            }

                        }

                    }

                }
            )
        }
    ) {

        Column {

            MyTopAppBar(
                scope = scope,
                drawerState = drawerState,
                viewModel = viewModel
            )
            LazyColumn(
                modifier = Modifier
                    .background(color = PurpleGrey80)
            ) {
                itemsIndexed(allTasks) { _, item ->
                    TaskCard(
                        task = item,
                        viewModel = viewModel,
                        onClick = { task -> onClick(task) }
                    )
                    Dialog(
                        task = item,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Dialog(
    task: Task,
    viewModel: MainViewModel
) {
    if (task.delete) {
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete"
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.dialog_delete),
                    fontSize = 18.sp
                )
            },
            onDismissRequest = {
                task.delete = false
                viewModel.updateTask(task)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteTask(task)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        fontSize = 16.sp
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        task.delete = false
                        viewModel.updateTask(task)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.cansel),
                        fontSize = 16.sp
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable fun TaskCard(
    task: Task,
    viewModel: MainViewModel,
    onClick: (Task) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            modifier =
            Modifier
                .padding(top = 10.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = task.nameTask,
                modifier = Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = { },
                        onDoubleClick = { onClick(task) },
                        onLongClick = {
                            task.delete = true
                            viewModel.updateTask(task)
                        }
                    ),
                maxLines = if (task.state) 16 else 1
                )

            Text(
                text = task.dateTask,
                fontSize = 12.sp,
                color = Color.Gray,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        task.state = !task.state
                        viewModel.updateTask(task)
                    },
                    modifier =
                    Modifier
                        .size(width = 40.dp, height = 20.dp)
                ) {
                    if (task.state) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Up"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Down"
                        )
                    }
                }
            }
        }
    }
}
