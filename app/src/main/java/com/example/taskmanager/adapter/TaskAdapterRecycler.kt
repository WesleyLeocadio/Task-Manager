package com.example.taskmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.domain.User
import com.example.taskmanager.viewholder.SubjectViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.domain.Subject
import com.example.taskmanager.domain.Task
import com.example.taskmanager.viewholder.TaskViewHolder

class TaskAdapterRecycler(var c: Context, var task: List<Task>) :
    RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.inflatertask, parent, false)
        return TaskViewHolder(view)

    }

    override fun getItemCount(): Int {
        return task.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var taskAtual = task.get(position)

        holder.description.text = taskAtual.description
        holder.dueData.text = taskAtual.dueDate
        holder.priority.text = "Alta"
        if (taskAtual.complete == 0) {
            holder.image.setImageResource(R.drawable.ic_todo)
        } else {
            holder.image.setImageResource(R.drawable.ic_done)
        }
    }


}