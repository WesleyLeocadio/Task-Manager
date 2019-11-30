package com.example.taskmanager.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.taskmanager.domain.User
import com.example.taskmanager.viewholder.SubjectViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.Subject
import com.example.taskmanager.domain.Task
import com.example.taskmanager.viewholder.TaskViewHolder
import java.util.ArrayList

class TaskAdapterRecycler(var c: Context, var task:MutableList<Task>) :

    RecyclerView.Adapter<TaskViewHolder>() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            c,
            AppDatabase::class.java, "task-bd"
        )
            .allowMainThreadQueries()
            .build()
    }

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

        holder.image.setOnClickListener {
            taskAtual.complete = 1
            db.taskDao().atualizar(taskAtual)
            notifyItemChanged(position)

        }



        holder.imageDelete.setOnClickListener {

            val id = taskAtual.id
            db.taskDao().deletar(taskAtual)
            Toast.makeText(c, "Deletou", Toast.LENGTH_SHORT).show()
            task.removeAt(position)
            notifyItemRemoved(position)

        }
    }


}