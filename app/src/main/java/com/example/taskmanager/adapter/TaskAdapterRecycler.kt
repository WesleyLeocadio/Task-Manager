package com.example.taskmanager.adapter

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
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

class TaskAdapterRecycler(var c: Context, var task: MutableList<Task>) :

    RecyclerView.Adapter<TaskViewHolder>() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            c,
            AppDatabase::class.java, "task-bd"
        )
            .allowMainThreadQueries()
            .build()
    }

    lateinit var dialog: Dialog
    lateinit var botaoYes: Button
    lateinit var botaoNo: Button
    lateinit var botaoSalvar: Button
    lateinit var botaoCancelar: Button

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
                Toast.makeText(c, R.string.tarefa_delete, Toast.LENGTH_SHORT).show()
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

        holder.imageEdit.setOnClickListener {
            dialog = Dialog(c)
            dialog.setContentView(R.layout.dialog_edit_task)
            dialog.setCancelable(true)

            var name = dialog.findViewById<TextView>(R.id.txtNameTaskDialog)
            var descricao = dialog.findViewById<TextView>(R.id.txtDescriptionTaskDialog)
            var date = dialog.findViewById<TextView>(R.id.txtDateDialog)
            var discplina = dialog.findViewById<TextView>(R.id.disciplinaDialog)

            var chec = dialog.findViewById<CheckBox>(R.id.ckcConcluida)


            name.text = taskAtual.name
            descricao.text = taskAtual.description
            date.text = taskAtual.dueDate
            discplina.text = "Disciplina: " + db.subjectDao().isSubjectId(taskAtual.subject).name
            Log.i("teste", "${taskAtual.complete}")
            chec.isChecked = taskAtual.complete == 1

            botaoSalvar = dialog.findViewById(R.id.save_alteracao)
            botaoSalvar.setOnClickListener {
                //Validação dos campos

                taskAtual.name = name.text.toString()
                taskAtual.description = descricao.text.toString()
                taskAtual.dueDate = date.text.toString()
                if (chec.isChecked) {
                    taskAtual.complete = 1
                    holder.image.setImageResource(R.drawable.ic_done)
                } else {
                    taskAtual.complete = 0
                    holder.image.setImageResource(R.drawable.ic_todo)
                }


                db.taskDao().atualizar(taskAtual)
                notifyItemChanged(position)
                dialog.dismiss()
            }

            botaoCancelar = dialog.findViewById(R.id.cancele_alteracao)
            botaoCancelar.setOnClickListener {

                dialog.dismiss()
            }

            dialog.show()
        }
    }


}