package com.example.tasksmaster.domain.repository

import androidx.lifecycle.LiveData
import com.example.tasksmaster.data.entities.Task

interface TaskRepository {
    fun getAllTasks(): LiveData<List<Task>>
    suspend fun insertTask(newTask: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}
