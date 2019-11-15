package com.example.taskmanager.business

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.Subject
import com.example.taskmanager.util.SecurityPreferences
import com.example.taskmanager.util.ValidationException

class SubjectBusiness (val context: Context) {

    private val db: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "task-bd").allowMainThreadQueries()
            .build()

    private val sharedPreferences: SecurityPreferences = SecurityPreferences(context)

    @Throws(ValidationException::class)

    fun insert(name: String, description: String) {
        try {
            Log.i("id","Name: ${name},  desc: ${description}")

            if (name.equals("") || description.equals("")) {
                throw ValidationException(context.getString(R.string.informar_campos))
            }
            if (db.subjectDao().isSubjectExistent(name)) {
                throw ValidationException("Disciplina já foi cadastrada!")
            }

            db.subjectDao().insert(Subject(name,description,sharedPreferences.getPreferences("USER_ID").toInt()))
            Toast.makeText(context,"Cadastro realizado!",Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            throw e
        }
    }
}