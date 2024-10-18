package com.example.tasksmaster.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tasksmaster.R
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.presentation.ui.theme.BlueMain
import com.example.tasksmaster.presentation.ui.theme.Category1
import com.example.tasksmaster.presentation.ui.theme.Category2
import com.example.tasksmaster.presentation.ui.theme.Category3
import com.example.tasksmaster.presentation.viewmodels.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextScreen(
    viewModel: MainViewModel,
    task: Task,
    navController: NavHostController,
    stateHelper: Boolean
) {
    var titleScreen by remember {
        mutableStateOf(task.titleTask)
    }
    val defaultTitleScreen = stringResource(id = R.string.newTaskText)
    var textScreen by remember {
        mutableStateOf(task.nameTask)
    }

    var save by remember {
        mutableStateOf(true)
    }

    val systemUiController = rememberSystemUiController()
    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                    actionIconContentColor = Color.Transparent
                ),
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val maxChar = 22
                        BasicTextField(
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            value = titleScreen,
                            onValueChange = { text ->
                                if (text.length <= maxChar) {
                                    titleScreen = text
                                    save = false
                                }
                            },
                        )
                        Text(
                            text = task.dateTask,
                            fontSize = 12.sp,
                            color = Color.Gray,
                        )
                        Text("Hello")
                    }
                },
                navigationIcon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                if ((textScreen != task.nameTask) or
                                    (titleScreen != task.titleTask)
                                ) {
                                    if (titleScreen.isBlank()) {
                                        titleScreen = defaultTitleScreen
                                    }
                                    task.nameTask = textScreen
                                    viewModel.updateTask(task)
                                }
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.baseline_arrow_back_24
                                ),
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                        if (stateHelper) {
                            Text(
                                text = stringResource(id = R.string.back),
                                fontSize = 12.sp,
                                modifier = Modifier.offset(y = (-10).dp)
                            )
                        }
                    }

                },
                actions = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            enabled = !save,
                            onClick = {
                                save = true
                                task.nameTask = textScreen
                                if (titleScreen.isBlank()) {
                                    titleScreen = defaultTitleScreen
                                }
                                task.titleTask = titleScreen
                                viewModel.updateTask(task)
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.outline_save_24),
                                contentDescription = "Save",
                                tint = if (save) Color.White else Color.Black
                            )
                        }
                        if (stateHelper) {
                            Text(
                                text = stringResource(id = R.string.save),
                                fontSize = 12.sp,
                                modifier = Modifier.offset(y = (-10).dp),
                                color = if (save) Color.White else Color.Black
                            )
                        }
                    }

                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            var colorCategory by remember {
                mutableIntStateOf(task.color)
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(colorCategory)
                ),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = Color.Transparent,
                            darkIcons = true
                        )
                    }
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_circle_24
                        ),
                        contentDescription = "circle1",
                        tint = BlueMain,
                        modifier = Modifier.clickable {
                            colorCategory = BlueMain.toArgb()
                            task.color = colorCategory
                            save = false
                        }
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_circle_24
                        ),
                        contentDescription = "circle2",
                        tint = Category1,
                        modifier = Modifier.clickable {
                            colorCategory = Category1.toArgb()
                            task.color = colorCategory
                            save = false
                        }
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_circle_24
                        ),
                        contentDescription = "circle3",
                        tint = Category2,
                        modifier = Modifier.clickable {
                            colorCategory = Category2.toArgb()
                            task.color = colorCategory
                            save = false
                        }
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_circle_24
                        ),
                        contentDescription = "circle4",
                        tint = Category3,
                        modifier = Modifier.clickable {
                            colorCategory = Category3.toArgb()
                            task.color = colorCategory
                            save = false
                        }
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_circle_24
                        ),
                        contentDescription = "circle1",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            colorCategory = Color.White.toArgb()
                            task.color = colorCategory
                            save = false
                        }
                    )
                }
            }
            BasicTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                textStyle = TextStyle(
                    fontSize = 18.sp
                ),
                value = textScreen,
                onValueChange = { text ->
                    textScreen = text
                    save = false
                }
            )
        }

    }
}
