package com.alisadmitrieva.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alisadmitrieva.todolist.database.TodoTaskDao

class ViewModelFactory(private val dataSource: TodoTaskDao) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoTaskViewModel::class.java)) {
            return TodoTaskViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
