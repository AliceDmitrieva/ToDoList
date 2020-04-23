package com.alisadmitrieva.todolist.data

import android.app.Application
import com.alisadmitrieva.todolist.data.database.TodoTask
import com.alisadmitrieva.todolist.data.database.TodoTaskDao
import com.alisadmitrieva.todolist.data.database.TodoTasksDatabase
import io.reactivex.Flowable

class TodoRepository(application: Application) {

    private val todoTaskDao: TodoTaskDao
    private val allTodos: Flowable<List<TodoTask>>

    init {
        val database = TodoTasksDatabase.getInstance(application.applicationContext)
        todoTaskDao = database!!.todoTaskDao()
        allTodos = todoTaskDao.getTodoTasks()
    }

    fun saveTodoTask(todo: TodoTask) {
        todoTaskDao.saveTodoTask(todo)
    }

    fun deleteTodoTask(todo: TodoTask) {
        todoTaskDao.deleteTodoTask(todo)
    }

    fun updateTodoTask(todo: TodoTask) {
        todoTaskDao.updateTodoTask(todo)
    }

    fun getTodoTasks(): Flowable<List<TodoTask>> {
        return allTodos
    }
}
