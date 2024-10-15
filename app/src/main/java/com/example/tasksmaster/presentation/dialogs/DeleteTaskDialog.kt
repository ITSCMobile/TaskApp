package com.example.tasksmaster.presentation.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.presentation.ui.theme.PinkEnd
import com.example.tasksmaster.presentation.ui.theme.PurpleMain
import com.example.tasksmaster.presentation.viewmodels.MainViewModel

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
