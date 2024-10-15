package com.example.tasksmaster.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasksmaster.data.dao.TaskDao
import com.example.tasksmaster.data.entities.Task

@Database(entities = [(Task::class)], version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        private var INSTANCE: TaskRoomDatabase? = null

        fun getInstance(context: Context): TaskRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskRoomDatabase::class.java,
                        "task_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
