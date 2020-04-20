package com.alisadmitrieva.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoTasksDatabase : RoomDatabase() {

    abstract fun todoTaskDao(): TodoTaskDao

    companion object {
        private var INSTANCE: TodoTasksDatabase? = null

        fun getInstance(context: Context): TodoTasksDatabase? {
            if (INSTANCE == null) {
                synchronized(TodoTasksDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        TodoTasksDatabase::class.java,
                        "todo_tasks_database"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
