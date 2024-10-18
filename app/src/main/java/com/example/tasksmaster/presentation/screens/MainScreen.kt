package com.example.tasksmaster.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.presentation.dialogs.Dialog
import com.example.tasksmaster.presentation.taskcards.MainTaskCard
import com.example.tasksmaster.presentation.ui.theme.Category3
import com.example.tasksmaster.presentation.ui.theme.PinkEnd
import com.example.tasksmaster.presentation.ui.theme.PinkSecond
import com.example.tasksmaster.presentation.ui.theme.PurpleMain
import com.example.tasksmaster.presentation.viewmodels.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope,
    stateHelper: Boolean,
    onClick: (Task) -> Unit,
    onStateHelper: (Boolean) -> Unit
) {

    val allTasks by viewModel.allTasks.observeAsState(listOf())
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
                    MainTaskCard(
                        task = item,
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
