package com.alisadmitrieva.todolist.data.database

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface TodoTaskDao {

    @Insert
    fun saveTodoTask(todoTask: TodoTask)

    @Delete
    fun deleteTodoTask(todoTask: TodoTask)

    @Update
    fun updateTodoTask(TodoTask: TodoTask)

    @Query("SELECT * FROM todo_tasks ORDER BY id DESC")
    fun getTodoTasks(): Flowable<List<TodoTask>>
}
