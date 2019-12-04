package com.example.taskmanager.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.taskmanager.viewholder.SubjectViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.TaskSubjectActivity
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.Subject

class SubjectAdapterRecycler(var c: Context, var subjects: MutableList<Subject>) :
    RecyclerView.Adapter<SubjectViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.inflatersubject, parent, false)
        return SubjectViewHolder(view)

    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        var subjectAtual = subjects.get(position)

        holder.name.text = "Disciplina:" + subjectAtual.name
        holder.description.text = "Descrição:" + subjectAtual.description

       holder.imageEdit.setOnClickListener {

            dialog = Dialog(c)
            dialog.setContentView(R.layout.dialog_edit_subject)
            dialog.setCancelable(true)

            var name = dialog.findViewById<TextView>(R.id.txtNameSubjectDialog)
            var descricao = dialog.findViewById<TextView>(R.id.txtDescriptionSubjectDialog)

            name.text = subjectAtual.name
            descricao.text = subjectAtual.description


            botaoSalvar = dialog.findViewById(R.id.save_alteracao_subject)
            botaoSalvar.setOnClickListener {
                //Validação dos campos

                if (name.text.toString() == "" || descricao.text.toString() == ""){
                    Toast.makeText(c,R.string.campos_preenchidos,Toast.LENGTH_SHORT).show()}
                else{
                    subjectAtual.name = name.text.toString()
                    subjectAtual.description = descricao.text.toString()

                    db.subjectDao().atualizar(subjectAtual)
                    notifyItemChanged(position)
                    dialog.dismiss()
                }
            }

            botaoCancelar = dialog.findViewById(R.id.cancele_alteracao_subject)
            botaoCancelar.setOnClickListener {

                dialog.dismiss()
            }

            dialog.show()
        }

        holder.imageDelete.setOnClickListener {
            dialog = Dialog(c)
            dialog.setContentView(R.layout.dialog_delete_subject)
            dialog.setCancelable(true)

            botaoYes = dialog.findViewById(R.id.yes)
            botaoYes.setOnClickListener {
                //Maneira errada. a anotacação cascata nao tá funcionando
                db.taskDao().deletAllTaskSubjectId(subjectAtual.id)
                db.subjectDao().deletar(subjectAtual)
                Toast.makeText(c, R.string.disciplina_delete, Toast.LENGTH_SHORT).show()
//                subjects.removeAt(position)
//                notifyItemRemoved(position)
                subjects.remove(subjectAtual)
                notifyDataSetChanged()
                dialog.dismiss()
            }

            botaoNo = dialog.findViewById(R.id.no)
            botaoNo.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
        holder.linear.setOnClickListener {
            var i = Intent(c, TaskSubjectActivity::class.java)
                        var b = Bundle()
                        b.putInt("id", subjectAtual.id)
                        i.putExtras(b)
                        c.startActivity(i)

        }
    }

    fun id(id: Int): Int {
        return subjects.get(id).id
    }


}