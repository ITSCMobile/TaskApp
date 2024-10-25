package com.example.tasksmaster.presentation.taskcards

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasksmaster.R
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.presentation.ui.theme.BlueMain
import com.example.tasksmaster.presentation.ui.theme.Category1
import com.example.tasksmaster.presentation.ui.theme.Category2
import com.example.tasksmaster.presentation.ui.theme.Category3
import com.example.tasksmaster.presentation.ui.theme.PinkEnd
import com.example.tasksmaster.presentation.viewmodels.MainViewModel

@Composable
fun MainTaskCard(
    task: Task,
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
            Column {
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
