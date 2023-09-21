package com.example.tasksmaster.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tasksmaster.objects.State
import com.example.tasksmaster.objects.Task
import com.example.tasksmaster.objects.TaskRepository
import com.example.tasksmaster.objects.TaskRoomDatabase

class MainViewModel(application: Application) : ViewModel() {
    val allTasks: LiveData<List<Task>>
    private val repository: TaskRepository
    val searchResults: MutableLiveData<List<State>>

    init {
        val taskDb = TaskRoomDatabase.getInstance(application)
        val taskDao = taskDb.taskDao()
        val stateDao = taskDb.stateDao()

        repository = TaskRepository(taskDao, stateDao)
        allTasks = repository.allTasks
        searchResults = repository.searchResults
    }

    fun insertTask(task: Task) {
        repository.insertTask(task)
    }
    fun insertState(state: State) {
        repository.insertState(state)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }
    fun updateState(state: State) {
        repository.updateState(state)
    }

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }
    fun deleteState(state: State) {
        repository.deleteState(state)
    }

    fun findState(id: Int): List<State>{
        return repository.findState(id)
    }
}
