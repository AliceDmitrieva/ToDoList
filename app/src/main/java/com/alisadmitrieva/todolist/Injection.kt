package com.alisadmitrieva.todolist

import android.content.Context
import com.alisadmitrieva.todolist.database.TodoTaskDao
import com.alisadmitrieva.todolist.database.TodoTasksDatabase

object Injection {

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideTodoTasksDataSource(context)
        return ViewModelFactory(dataSource)
    }

    fun provideTodoTasksDataSource(context: Context): TodoTaskDao {
        val database = TodoTasksDatabase.getInstance(context)
        return database!!.todoTaskDao()
    }
}
