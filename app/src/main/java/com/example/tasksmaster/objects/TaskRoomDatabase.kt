package com.example.tasksmaster.objects

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Task::class), (State::class)], version = 1)
abstract class TaskRoomDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun stateDao(): StateDao

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
