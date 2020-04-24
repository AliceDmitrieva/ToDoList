package com.alisadmitrieva.todolist.ui.todotasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alisadmitrieva.todolist.R
import com.alisadmitrieva.todolist.data.database.TodoTask
import kotlinx.android.synthetic.main.todotask_item.view.*

class TodoTasksAdapter(todoTasksEvents: TodoTasksEvents) :
    RecyclerView.Adapter<TodoTasksAdapter.ViewHolder>() {

    private var todoTasks = mutableListOf<TodoTask>()
    private val listener: TodoTasksEvents = todoTasksEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todotask_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = todoTasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoTasks[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: TodoTask, listener: TodoTasksEvents) {
            itemView.titleTextView.text = todo.title
            itemView.todotaskTextTextView.text = todo.text
            itemView.deleteIcon.setOnClickListener {
                listener.onDeleteClicked(todo)
            }

            itemView.setOnClickListener {
                listener.onTodoTaskClicked(todo)
            }
        }
    }

    fun setTodoTasks(todoItems: MutableList<TodoTask>) {
        this.todoTasks = todoItems
        notifyDataSetChanged()
    }

    fun updateData() {
        notifyDataSetChanged()
    }

    interface TodoTasksEvents {
        fun onDeleteClicked(todoTask: TodoTask)
        fun onTodoTaskClicked(todoTask: TodoTask)
    }
}
