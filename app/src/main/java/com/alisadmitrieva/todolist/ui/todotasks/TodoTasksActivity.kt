package com.alisadmitrieva.todolist.ui.todotasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alisadmitrieva.todolist.R
import com.alisadmitrieva.todolist.data.database.TodoTask
import com.alisadmitrieva.todolist.ui.createtodotask.CreateTodoTaskActivity
import com.alisadmitrieva.todolist.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_todotasks.*

class TodoTasksActivity : AppCompatActivity(), TodoTasksAdapter.TodoTasksEvents {

    private lateinit var todoTasksAdapter: TodoTasksAdapter
    private lateinit var todoViewModel: TodoTaskViewModel
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todotasks)
        todoViewModel = ViewModelProvider(this).get(TodoTaskViewModel::class.java)

        todotasksRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        todoTasksAdapter = TodoTasksAdapter(this)
        todotasksRecyclerView.adapter = todoTasksAdapter

        createTodotaskFab.setOnClickListener {
            val intent = Intent(this@TodoTasksActivity, CreateTodoTaskActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CREATE_TODO)
        }
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
            todoViewModel.getToDoTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    todoTasksAdapter.setTodoTasks(it)
                },
                    { error -> Log.e(TAG, "Unable to get todo tasks", error) })
        )
    }


    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    override fun onTodoTaskClicked(todoTask: TodoTask) {
        val intent = Intent(this@TodoTasksActivity, CreateTodoTaskActivity::class.java)
        intent.putExtra(Constants.INTENT_TODOTASK_OBJECT, todoTask)
        startActivityForResult(intent, Constants.INTENT_UPDATE_TODO)
    }

    override fun onDeleteClicked(todoTask: TodoTask) {
        deleteTodoTask(todoTask)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val todoTask = data?.getParcelableExtra<TodoTask>(Constants.INTENT_TODOTASK_OBJECT)!!
            when (requestCode) {
                Constants.INTENT_CREATE_TODO -> {
                    saveTodoTask(todoTask)
                }
                Constants.INTENT_UPDATE_TODO -> {
                    updateTodoTask(todoTask)
                }
            }
        }
    }

    private fun saveTodoTask(todoTask: TodoTask) {
        disposable.add(
            todoViewModel.saveTodoTask(todoTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    todoTasksAdapter.updateData()
                },
                    { error -> Log.e(TAG, "Unable to save todo task", error) })
        )
    }

    private fun updateTodoTask(todoTask: TodoTask) {
        disposable.add(
            todoViewModel.updateTodoTask(todoTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    todoTasksAdapter.updateData()
                },
                    { error -> Log.e(TAG, "Unable to update todo task", error) })
        )
    }

    private fun deleteTodoTask(todoTask: TodoTask) {
        disposable.add(
            todoViewModel.deleteTodoTask(todoTask)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    todoTasksAdapter.updateData()
                },
                    { error -> Log.e(TAG, "Unable to delete todo task", error) })
        )
    }

    companion object {
        private val TAG = TodoTasksActivity::class.java.simpleName
    }
}
