package com.example.tasksmaster.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.tasksmaster.R
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.view.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    viewModel: MainViewModel
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Sett"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    val task = Task(
                        taskName = "Введите текст",
                        taskDate = "Дата"
                    )
                    viewModel.insertTask(task)
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    )
}
