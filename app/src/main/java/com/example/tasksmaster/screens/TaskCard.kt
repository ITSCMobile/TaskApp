package com.example.tasksmaster.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.ui.theme.Pink80
import com.example.tasksmaster.ui.theme.Purple80
import com.example.tasksmaster.ui.theme.PurpleGrey80
import com.example.tasksmaster.view.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreenSetup(
    viewModel: MainViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope,
    stateHelper: Boolean,
    onClick: (Task) -> Unit,
    onStateHelper: (Boolean) -> Unit
    ) {

    val allTasks by viewModel.allTasks.observeAsState(listOf())
    var stateTheme by remember {
        mutableStateOf(true)
    }


    ModalNavigationDrawer(
        modifier = Modifier
            .background(PurpleGrey80),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = PurpleGrey80,
                content = {
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
                        //Смена темы хз вообще))
/*                        Row(
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
                                checked = stateTheme,
                                onCheckedChange = {
                                    stateTheme = it
                                },
                                colors = SwitchDefaults.colors(
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Purple80
                                ),
                                thumbContent = {
                                    if (stateTheme) {
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
                        }*/
                        //Смена языка, хз как реализовать)))
                        /*Row(
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

                        }*/
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.helper),
                                fontSize = 18.sp
                            )
                            Switch(
                                checked = stateHelper,
                                onCheckedChange = {
                                    onStateHelper(it)
                                },
                                colors = SwitchDefaults.colors(
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Purple80
                                )
                            )
                        }

                    }

                }
            )
        }
    ) {

        Scaffold(
            topBar = {
                val newTaskText = stringResource(id = R.string.newTaskText)
                CenterAlignedTopAppBar(
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    navigationIcon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Sett"
                                )
                            }
                            if (stateHelper){
                                Text(
                                    text = stringResource(id = R.string.settings),
                                    fontSize = 12.sp,
                                    modifier = Modifier.offset(y = (-10).dp)
                                )
                            }
                        }

                    },
                    actions = {
                        IconButton(
                            onClick = {
                                val currDate =
                                    LocalDateTime.now().format(
                                        DateTimeFormatter.ofPattern("dd MMM yyyy")
                                    )
                                val task = Task(
                                    taskName = newTaskText,
                                    taskDate = currDate
                                )
                                viewModel.insertTask(task)
                            }) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add"
                                )
                                if (stateHelper){
                                    Text(
                                        text = stringResource(id = R.string.newTask),
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                )
            }

        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                itemsIndexed(allTasks) { _, item ->
                    TaskCard(
                        task = item,
                        stateHelper = stateHelper,
                        viewModel = viewModel,
                        onClick = { task -> onClick(task) }
                    )
                    if (item.delete){
                        Dialog(
                            task = item,
                            viewModel = viewModel
                        )
                    }
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
                    text = stringResource(id = R.string.cancel),
                    fontSize = 16.sp
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable fun TaskCard(
    task: Task,
    stateHelper: Boolean,
    viewModel: MainViewModel,
    onClick: (Task) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(
                onClick = {
                    onClick(task)
                }
            ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        var expanded by remember { mutableStateOf(false) }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 0.dp
                )
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.nameTask,
                    maxLines = if (task.state) 16 else 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = task.dateTask,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
            if (stateHelper and !task.state){
                Column {
                    Text(
                        text = stringResource(id = R.string.helpWrite),
                        fontSize = 12.sp,
                        color = Color.Gray,
                    )
                }
            }
            if (!task.state) {
                Column {
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier
                            .heightIn(max = 20.dp, min = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "moreVert",
                            modifier = Modifier
                                .heightIn(max = 20.dp, min = 20.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.delete))
                            },
                            onClick = {
                                expanded = !expanded
                                task.delete = true
                                viewModel.updateTask(task)
                            }
                        )
                    }
                }
            }
        }
        IconButton(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 20.dp),
            onClick = {
                task.state = !task.state
                viewModel.updateTask(task)
            }
        ) {
            val icon =
                if (task.state)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown
            Icon(
                imageVector = icon,
                contentDescription = "Up"
            )
        }

    }
}
