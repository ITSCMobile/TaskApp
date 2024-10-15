package com.example.tasksmaster.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tasksmaster.data.entities.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>
}
