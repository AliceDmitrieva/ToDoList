package com.alisadmitrieva.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alisadmitrieva.todolist.data.TodoRepository
import com.alisadmitrieva.todolist.data.database.TodoTask
import io.reactivex.Flowable

class TodoTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository = TodoRepository(application)

    fun getToDoTasks(): Flowable<List<TodoTask>> {
        return repository.getTodoTasks()
    }

    fun saveTodoTask(todo: TodoTask) {
        repository.saveTodoTask(todo)
    }

    fun deleteTodoTask(todo: TodoTask) {
        repository.deleteTodoTask(todo)
    }

    fun updateTodoTask(todo: TodoTask) {
        repository.updateTodoTask(todo)
    }
}
