package com.alisadmitrieva.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoTaskDao {

    @Insert
    suspend fun saveTodoTask(todoTask: TodoTask)

    @Delete
    suspend fun deleteTodoTask(todoTask: TodoTask)

    @Update
    suspend fun updateTodoTask(TodoTask: TodoTask)

    @Query("SELECT * FROM todo_tasks ORDER BY id DESC")
    fun getTodoTasks(): LiveData<List<TodoTask>>
}
