package com.example.tasksmaster.screens

import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tasksmaster.R
import com.example.tasksmaster.objects.Routes
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.objects.TaskRoomDatabase
import com.example.tasksmaster.view.MainViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextScreen(
    viewModel: MainViewModel,
    task: Task,
    navController: NavHostController,
    stateHelper: Boolean
) {
    var text = task.nameTask
    if (task.nameTask == stringResource(id = R.string.newTaskText)) {
        text = ""
    }
    var textScreen by remember {
        mutableStateOf(text)
    }

    var save by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                if (textScreen != task.nameTask) {
                                    task.nameTask = textScreen
                                    viewModel.updateTask(task)
                                }
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        if (stateHelper){
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
                    ){
                        IconButton(
                            enabled = !save,
                            onClick = {
                                save = true
                                task.nameTask = textScreen
                                viewModel.deleteTask(task)
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.outline_save_24),
                                contentDescription = "Back",
                                tint = if (save) Color.White else Color.Black
                            )
                        }
                        if (stateHelper){
                            Text(
                                text = stringResource(id = R.string.save),
                                fontSize = 12.sp,
                                modifier = Modifier.offset(y = (-10).dp),
                                color = if (save) Color.White else Color.Black
                            )
                        }
                    }

                },
                title = {}
            )
        }
    ) { paddingValues ->
        BasicTextField(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = 0.dp,
                    bottom = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                )
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
