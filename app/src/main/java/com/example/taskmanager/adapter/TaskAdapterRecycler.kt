package com.example.taskmanager.adapter

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

    lateinit var  dialog: Dialog
    lateinit var botaoYes: Button
    lateinit var botaoNo: Button

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.inflatertask, parent, false)
        return TaskViewHolder(view)

    }

    override fun getItemCount(): Int {
        return task.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var taskAtual = task.get(position)

        holder.description.text = taskAtual.name
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

            dialog = Dialog(c)
            dialog.setContentView(R.layout.dialog_delete)
            dialog.setCancelable(true)

            botaoYes = dialog.findViewById(R.id.yes)
            botaoYes.setOnClickListener {
                //val id = taskAtual.id
                db.taskDao().deletar(taskAtual)
                Toast.makeText(c,R.string.tarefa_delete, Toast.LENGTH_SHORT).show()
                task.removeAt(position)
                notifyItemRemoved(position)
                dialog.dismiss()
            }

            botaoNo = dialog.findViewById(R.id.no)
            botaoNo.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }


}