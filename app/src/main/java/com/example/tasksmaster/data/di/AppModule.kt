package com.example.tasksmaster.data.di

import androidx.room.Room
import com.example.tasksmaster.data.database.TaskRoomDatabase
import com.example.tasksmaster.data.repository.TaskRepositoryImpl
import com.example.tasksmaster.domain.repository.TaskRepository
import com.example.tasksmaster.presentation.viewmodels.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(get(), TaskRoomDatabase::class.java, "task_database").fallbackToDestructiveMigration().build() }
    single { get<TaskRoomDatabase>().taskDao() }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
}
