package com.example.tasksmaster.data.repositories

import androidx.lifecycle.LiveData
import com.example.tasksmaster.data.dao.TaskDao
import com.example.tasksmaster.data.entities.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertTask(newTask: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.insertTask(newTask)
        }
    }

    fun deleteTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }
}