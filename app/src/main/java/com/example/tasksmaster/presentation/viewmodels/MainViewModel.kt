package com.example.tasksmaster.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tasksmaster.data.database.TaskRoomDatabase
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.data.repositories.TaskRepository

class MainViewModel(application: Application) : ViewModel() {
    val allTasks: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDb = TaskRoomDatabase.getInstance(application)
        val taskDao = taskDb.taskDao()

        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun insertTask(task: Task) {
        repository.insertTask(task)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }
}
