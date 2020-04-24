package com.alisadmitrieva.todolist.data

import android.app.Application
import com.alisadmitrieva.todolist.data.database.TodoTask
import com.alisadmitrieva.todolist.data.database.TodoTaskDao
import com.alisadmitrieva.todolist.data.database.TodoTasksDatabase
import io.reactivex.Completable
import io.reactivex.Flowable

class TodoRepository(application: Application) {

    private val todoTaskDao: TodoTaskDao
    private val todoTasks: Flowable<MutableList<TodoTask>>

    init {
        val database = TodoTasksDatabase.getInstance(application.applicationContext)
        todoTaskDao = database!!.todoTaskDao()
        todoTasks = todoTaskDao.getTodoTasks()
    }

    fun saveTodoTask(todo: TodoTask): Completable {
        return todoTaskDao.saveTodoTask(todo)
    }

    fun deleteTodoTask(todo: TodoTask): Completable {
        return todoTaskDao.deleteTodoTask(todo)
    }

    fun updateTodoTask(todo: TodoTask): Completable {
        return todoTaskDao.updateTodoTask(todo)
    }

    fun getTodoTasks(): Flowable<MutableList<TodoTask>> {
        return todoTasks
    }
}
