package com.example.tasksmaster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tasksmaster.data.dao.TaskDao
import com.example.tasksmaster.data.entities.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
