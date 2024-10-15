package com.example.tasksmaster.data.entities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "taskId")
    var id: Int = 0,

    @ColumnInfo(name = "taskTitle")
    var titleTask: String = "",

    @ColumnInfo(name = "taskName")
    var nameTask: String = "",

    @ColumnInfo(name = "taskDate")
    var dateTask: String = "",

    var state: Boolean = false,
    var delete: Boolean = false,
    var color: Int = Color.White.toArgb()
)
