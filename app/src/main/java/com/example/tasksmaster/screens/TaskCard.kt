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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.ui.theme.BlueMain
import com.example.tasksmaster.ui.theme.Category1
import com.example.tasksmaster.ui.theme.Category2
import com.example.tasksmaster.ui.theme.Category3
import com.example.tasksmaster.ui.theme.PinkEnd
import com.example.tasksmaster.ui.theme.PinkSecond
import com.example.tasksmaster.ui.theme.PurpleMain
import com.example.tasksmaster.view.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
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
    /*    var stateTheme by remember {
            mutableStateOf(true)
        }*/

    val systemUiController = rememberSystemUiController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                drawerShape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 20.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 20.dp
                ),
                content = {
                    Scaffold(
                        containerColor = Color.White,
                        topBar = {
                            Text(
                                text = stringResource(id = R.string.settings),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 15.dp),
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        },
                        bottomBar = {
                            Text(
                                text = "by L3on1kL\n" +
                                        "beta v.0.3.2",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 5.dp)
                                    .alpha(0.4f),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Gray
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = it.calculateTopPadding(),
                                    bottom = 0.dp,
                                    start = 15.dp,
                                    end = 15.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
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
                                    fontSize = 18.sp,
                                )
                                Switch(
                                    checked = stateHelper,
                                    onCheckedChange = {
                                        onStateHelper(it)
                                    },
                                    colors = SwitchDefaults.colors(
                                        uncheckedThumbColor = Color.Gray,
                                        uncheckedTrackColor = Color.Transparent,
                                        checkedThumbColor = Category3,
                                        checkedTrackColor = Color.Transparent,
                                        uncheckedBorderColor = Color.Transparent,
                                        checkedBorderColor = Color.Transparent
                                    )
                                )
                            }

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
                    modifier = Modifier.background(
                        Brush.verticalGradient(
                            colors = listOf(
                                PurpleMain,
                                PinkSecond
                            )
                        )
                    ),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            color = Color.White
                        )
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
                                    contentDescription = "Sett",
                                    tint = Color.White
                                )
                            }
                            if (stateHelper) {
                                Text(
                                    text = stringResource(id = R.string.settings),
                                    fontSize = 12.sp,
                                    modifier = Modifier.offset(y = (-10).dp),
                                    color = Color.White
                                )
                            }
                        }

                    },
                    actions = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {
                                    val currDate =
                                        LocalDateTime.now().format(
                                            DateTimeFormatter.ofPattern("dd MMM yyyy")
                                        )
                                    val task = Task(
                                        taskTitle = newTaskText,
                                        taskName = "",
                                        taskDate = currDate
                                    )
                                    viewModel.insertTask(task)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color.White
                                )
                            }
                            if (stateHelper) {
                                Text(
                                    text = stringResource(id = R.string.newTask),
                                    fontSize = 12.sp,
                                    modifier = Modifier.offset(y = (-10).dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                )
            }

        ) {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = PurpleMain,
                    darkIcons = false
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                PinkSecond,
                                PinkEnd
                            )
                        )
                    )
            ) {
                itemsIndexed(allTasks) { _, item ->
                    TaskCard(
                        task = item,
                        //stateHelper = stateHelper,
                        viewModel = viewModel,
                        onClick = { task -> onClick(task) }
                    )
                    if (item.delete) {
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
        containerColor = PinkEnd,
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = PurpleMain
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.dialog_delete),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
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
                    fontSize = 16.sp,
                    color = Color.Red
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
                    fontSize = 16.sp,
                    color = PurpleMain
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskCard(
    task: Task,
    //stateHelper: Boolean,
    viewModel: MainViewModel,
    onClick: (Task) -> Unit
) {
    var colorCategory by remember {
        mutableIntStateOf(task.color)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 0.dp
            )
            .clickable(
                onClick = {
                    onClick(task)
                }
            ),
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(colorCategory)
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(0.dp)
        ) {
            var expanded by remember { mutableStateOf(false) }
            Column() {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 10.dp,
                            start = 10.dp,
                            end = 0.dp,
                            bottom = 0.dp
                        )
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = task.titleTask,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = task.dateTask,
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                    }
                    Column {
                        IconButton(
                            onClick = { expanded = !expanded },
                            modifier = Modifier
                                .heightIn(max = 20.dp, min = 20.dp)
                                .wrapContentWidth(align = Alignment.End)
                                .wrapContentHeight(align = Alignment.Top)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "moreVert",
                                modifier = Modifier
                                    .heightIn(max = 20.dp, min = 20.dp),
                                tint = if (Color(colorCategory) == Color.White) Color.Black
                                else Color(colorCategory)
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(PinkEnd)
                        ) {
                            var category by remember { mutableStateOf(false) }
                            DropdownMenuItem(
                                text = {
                                    Row {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                id = R.drawable.baseline_circle_24
                                            ),
                                            contentDescription = "circle1",
                                            tint = BlueMain,
                                            modifier = Modifier.clickable {
                                                expanded = !expanded
                                                colorCategory = BlueMain.toArgb()
                                                task.color = colorCategory
                                                viewModel.updateTask(task)
                                            }
                                        )
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                id = R.drawable.baseline_circle_24
                                            ),
                                            contentDescription = "circle2",
                                            tint = Category1,
                                            modifier = Modifier.clickable {
                                                expanded = !expanded
                                                colorCategory = Category1.toArgb()
                                                task.color = colorCategory
                                                viewModel.updateTask(task)
                                            }
                                        )
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                id = R.drawable.baseline_circle_24
                                            ),
                                            contentDescription = "circle3",
                                            tint = Category2,
                                            modifier = Modifier.clickable {
                                                expanded = !expanded
                                                colorCategory = Category2.toArgb()
                                                task.color = colorCategory
                                                viewModel.updateTask(task)
                                            }
                                        )
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                id = R.drawable.baseline_circle_24
                                            ),
                                            contentDescription = "circle4",
                                            tint = Category3,
                                            modifier = Modifier.clickable {
                                                expanded = !expanded
                                                colorCategory = Category3.toArgb()
                                                task.color = colorCategory
                                                viewModel.updateTask(task)
                                            }
                                        )
                                        Icon(
                                            imageVector = ImageVector.vectorResource(
                                                id = R.drawable.baseline_circle_24
                                            ),
                                            contentDescription = "circle1",
                                            tint = Color.White,
                                            modifier = Modifier.clickable {
                                                expanded = !expanded
                                                colorCategory = Color.White.toArgb()
                                                task.color = colorCategory
                                                viewModel.updateTask(task)
                                            }
                                        )
                                    }
                                },
                                onClick = { category = !category },
                                modifier = Modifier.height(25.dp)
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(text = stringResource(id = R.string.delete))
                                },
                                onClick = {
                                    expanded = !expanded
                                    task.delete = true
                                    viewModel.updateTask(task)
                                },
                                modifier = Modifier.height(25.dp)
                            )
                        }
                    }
                }
                if (task.state) {
                    Text(
                        text = task.nameTask,
                        modifier = Modifier
                            .padding(
                                top = 0.dp,
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 0.dp
                            )
                    )
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
                        contentDescription = "Up",
                        tint = if (Color(colorCategory) == Color.White) Color.Black
                        else Color(colorCategory)
                    )
                }
            }
        }
    }
}


