package com.example.tasksmaster.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasksmaster.data.entities.Task
import com.example.tasksmaster.domain.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTasks: LiveData<List<Task>> = repository.getAllTasks()

    fun insertTask(task: Task) {
        viewModelScope.launch { repository.insertTask(task) }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch { repository.updateTask(task) }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { repository.deleteTask(task) }
    }
}
