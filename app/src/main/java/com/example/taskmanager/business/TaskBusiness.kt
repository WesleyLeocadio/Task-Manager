package com.example.taskmanager.business

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.*
import androidx.room.Room
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.Task
import com.example.taskmanager.util.SecurityPreferences
import com.example.taskmanager.util.ValidationException

class TaskBusiness (val context: Context) {

    private val db: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "task-bd").allowMainThreadQueries()
            .build()

    private val sharedPreferences: SecurityPreferences = SecurityPreferences(context)

    @Throws(ValidationException::class)

    fun SpinnerTeste(texto: TextView,menu: Spinner){

        var disciplinas = (db.subjectDao().listAll())
        var names = Array(disciplinas.size, { i -> i.toString() })
        for (i in 0 until disciplinas.size) {
            names[i] = disciplinas[i].name
        }

        menu.adapter = ArrayAdapter(this.context,android.R.layout.simple_spinner_dropdown_item,names)

        menu.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context,"Por favor, selecione uma opção",Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                texto.text = names[position]
            }

        }
    }
    fun insert(name: String, description: String, date: String) {
        try {
            Log.i("id","Name: ${name},  desc: ${description}, date: ${date},")

            if (name.equals("") || description.equals("") || date.equals("")) {
                throw ValidationException(context.getString(R.string.informar_campos))
            }
            if (db.taskDao().isTaskExistent(name)) {
                throw ValidationException(context.getString(R.string.tarefa_cadastrada))

            }

            db.taskDao().insert(Task(name,description,date,1,sharedPreferences.getPreferences("USER_ID").toInt()))
            Toast.makeText(context,"Cadastro realizado!", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            throw e
        }

    }
}