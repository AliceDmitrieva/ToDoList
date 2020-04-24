package com.alisadmitrieva.todolist.data.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TodoTaskDao {

    @Insert
    fun saveTodoTask(todoTask: TodoTask): Completable

    @Delete
    fun deleteTodoTask(todoTask: TodoTask): Completable

    @Update
    fun updateTodoTask(TodoTask: TodoTask): Completable

    @Query("SELECT * FROM todo_tasks ORDER BY id DESC")
    fun getTodoTasks(): Flowable<MutableList<TodoTask>>
}
