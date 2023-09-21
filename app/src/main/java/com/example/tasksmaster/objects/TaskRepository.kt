package com.example.tasksmaster.objects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao, private val stateDao: StateDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    val searchResults = MutableLiveData<List<State>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertTask(newTask: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.insertTask(newTask)
        }
    }
    fun insertState(newState: State) {
        coroutineScope.launch(Dispatchers.IO) {
            stateDao.insertState(newState)
        }
    }

    fun deleteTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }
    fun deleteState(state: State) {
        coroutineScope.launch(Dispatchers.IO) {
            stateDao.deleteState(state)
        }
    }

    fun updateTask(task: Task){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }
    fun updateState(state: State){
        coroutineScope.launch(Dispatchers.IO) {
            stateDao.updateState(state)
        }
    }

    fun findState(id: Int): List<State> {
        var ret: List<State> = List(1) { State() }
        coroutineScope.launch(Dispatchers.IO) {
            ret = stateDao.findState(id)
        }
        return ret
    }

}