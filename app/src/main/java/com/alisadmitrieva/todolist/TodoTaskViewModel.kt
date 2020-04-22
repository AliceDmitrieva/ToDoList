package com.alisadmitrieva.todolist

import androidx.lifecycle.ViewModel
import com.alisadmitrieva.todolist.database.TodoTask
import com.alisadmitrieva.todolist.database.TodoTaskDao
import io.reactivex.Flowable

class TodoTaskViewModel(private val todoTaskDao: TodoTaskDao) : ViewModel() {

    fun getToDoTasks(): Flowable<List<TodoTask>> {
        return todoTaskDao.getTodoTasks()
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
}
