package com.example.tasksmaster.objects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    val searchResults = MutableLiveData<List<Task>>()

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

    fun updateTask(task: Task){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }

    fun findTask(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Task>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async taskDao.findTask(name)
        }
}