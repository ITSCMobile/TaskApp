package com.example.tasksmaster.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.objects.TaskRepository
import com.example.tasksmaster.objects.TaskRoomDatabase

class MainViewModel(application: Application) : ViewModel() {
    val allTasks: LiveData<List<Task>>
    private val repository: TaskRepository
    val searchResults: MutableLiveData<List<Task>>

    init {
        val taskDb = TaskRoomDatabase.getInstance(application)
        val taskDao = taskDb.taskDao()
        repository = TaskRepository(taskDao)

        allTasks = repository.allTasks
        searchResults = repository.searchResults
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