package com.example.tasksmaster.data.repository

import androidx.lifecycle.LiveData
import com.example.tasksmaster.data.dao.TaskDao
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override fun getAllTasks(): LiveData<List<Task>> = taskDao.getAllTasks()

    override suspend fun insertTask(newTask: Task) {
        taskDao.insertTask(newTask)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}
