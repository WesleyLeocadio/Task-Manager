package com.example.taskmanager.adapter

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.Task
import com.example.taskmanager.viewholder.TaskViewHolder
import java.util.*

class TaskAdapterRecycler(var c: Context, var task: MutableList<Task>, var x: Int) :

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
    lateinit var buttonCalendar: ImageButton

    val calendario = Calendar.getInstance()
    val year = calendario.get(Calendar.YEAR)
    val month = calendario.get(Calendar.MONTH)
    val day = calendario.get(Calendar.DAY_OF_MONTH)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.inflatertask, parent, false)
        return TaskViewHolder(view)

    }

    override fun getItemCount(): Int {
        return task.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var taskAtual = task.get(position)

        holder.description.text = "Tarefa:" + taskAtual.name
        holder.dueData.text = "Vencimento:" + taskAtual.dueDate
        holder.priority.text = "Alta"
        if (taskAtual.complete == 0) {
            holder.image.setImageResource(R.drawable.ic_todo)
        } else {
            holder.image.setImageResource(R.drawable.ic_done)
        }

        holder.image.setOnClickListener {
            if (x == 0) {
                taskAtual.complete = 1
                db.taskDao().atualizar(taskAtual)
                notifyItemChanged(position)
                task.removeAt(position)
                notifyItemRemoved(position)
                Toast.makeText(c, "A tarefa ${taskAtual.name} foi concluída!", Toast.LENGTH_SHORT)
                    .show()


            } else if (x == 1) {
                taskAtual.complete = 0
                db.taskDao().atualizar(taskAtual)
                notifyItemChanged(position)
                task.removeAt(position)
                notifyItemRemoved(position)
                Toast.makeText(c, "A tarefa ${taskAtual.name} foi aberta!", Toast.LENGTH_SHORT)
                    .show()

            } else {

                if (taskAtual.complete == 0) {
                    taskAtual.complete = 1
                    db.taskDao().atualizar(taskAtual)
                    notifyItemChanged(position)
                    Toast.makeText(
                        c,
                        "A tarefa ${taskAtual.name} foi concluída!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

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

            botaoSalvar = dialog.findViewById(R.id.save_alteracao_subject)
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

            botaoCancelar = dialog.findViewById(R.id.cancele_alteracao_subject)
            botaoCancelar.setOnClickListener {

                dialog.dismiss()
            }

            buttonCalendar = dialog.findViewById(R.id.calendarDialog)
            buttonCalendar.setOnClickListener {
                val dpd = DatePickerDialog(c,
                    DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->
                        date.text = "${mDay}/${mMonth + 1}/${mYear}"
                    }, year, month, day
                )
                dpd.show()
            }

            dialog.show()
        }
    }


}