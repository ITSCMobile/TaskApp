package com.example.tasksmaster.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "taskId")
    var id: Int = 0

    @ColumnInfo(name = "taskName")
    var nameTask: String = ""

    @ColumnInfo(name = "taskDate")
    var dateTask: String = ""

    var state: Boolean = false
    var delete: Boolean = false

    constructor()

    constructor(taskName: String, taskDate: String) {
        this.nameTask = taskName
        this.dateTask = taskDate
        this.state = false
        this.delete = false
    }
}