package com.example.tasksmaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.view.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextScreen(
    viewModel: MainViewModel,
    task: Task,
    onClick: (Unit) -> Unit
) {
    var textScreen by remember {
        mutableStateOf(task.nameTask)
    }

    var save by remember {
        mutableStateOf(true)
    }

    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(
                    onClick = {
                            onClick(Unit)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(
                    enabled = !save,
                    onClick = {
                        save = true
                        task.nameTask = textScreen
                        viewModel.updateTask(task)
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.outline_save_24),
                        contentDescription = "Back",
                        tint = if (save) Color.White else Color.Black
                    )
                }
            },
            title = { /*TODO*/ }
        )


        BasicTextField(
            modifier = Modifier
                .padding(20.dp),
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            value = textScreen,
            onValueChange = {
                textScreen = it
                save = false
            }
        )
    }

}
