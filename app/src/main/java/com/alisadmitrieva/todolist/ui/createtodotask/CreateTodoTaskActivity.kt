package com.alisadmitrieva.todolist.ui.createtodotask

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alisadmitrieva.todolist.R
import com.alisadmitrieva.todolist.data.database.TodoTask
import com.alisadmitrieva.todolist.utils.Constants
import kotlinx.android.synthetic.main.activity_create_todotask.*

class CreateTodoTaskActivity : AppCompatActivity() {

    var todoTask: TodoTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todotask)

        if (isEditing()) {
            val todoTask: TodoTask = intent.getParcelableExtra(Constants.INTENT_TODOTASK_OBJECT)
            this.todoTask = todoTask
            showTodoTaskData(todoTask)
        }
    }

    private fun isEditing(): Boolean = intent != null && intent.hasExtra(Constants.INTENT_TODOTASK_OBJECT)

    private fun showTodoTaskData(todoTask: TodoTask) {
        titleEditText.setText(todoTask.title)
        todotaskTextEditText.setText(todoTask.text)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflate = menuInflater
        menuInflate.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.save_todotask -> {
                saveTodoTask()
            }
        }
        return true
    }

    private fun saveTodoTask() {
        if (validateInputFields()) {
            val id = if (todoTask != null) todoTask?.id else null
            val todo = TodoTask(
                id = id,
                title = titleEditText.text.toString(),
                text = todotaskTextEditText.text.toString()
            )
            val intent = Intent()
            intent.putExtra(Constants.INTENT_TODOTASK_OBJECT, todo)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun validateInputFields(): Boolean {
        if (titleEditText.text.isEmpty()) {
            titleTextTIL.error = getString(R.string.blankTitle)
            titleEditText.requestFocus()
            return false
        }
        if (todotaskTextEditText.text.isEmpty()) {
            todoTaskTextTIL.error = getString(R.string.blankText)
            todotaskTextEditText.requestFocus()
            return false
        }
        return true
    }
}

