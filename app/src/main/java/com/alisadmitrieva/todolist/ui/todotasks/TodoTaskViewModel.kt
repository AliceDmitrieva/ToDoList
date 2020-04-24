package com.alisadmitrieva.todolist.ui.todotasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alisadmitrieva.todolist.data.TodoRepository
import com.alisadmitrieva.todolist.data.database.TodoTask
import io.reactivex.Completable
import io.reactivex.Flowable

class TodoTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository = TodoRepository(application)

    fun getToDoTasks(): Flowable<MutableList<TodoTask>> {
        return repository.getTodoTasks()
    }

    fun saveTodoTask(todo: TodoTask): Completable {
        return repository.saveTodoTask(todo)
    }

    fun deleteTodoTask(todo: TodoTask): Completable {
        return repository.deleteTodoTask(todo)
    }

    fun updateTodoTask(todo: TodoTask): Completable {
        return repository.updateTodoTask(todo)
    }
}
