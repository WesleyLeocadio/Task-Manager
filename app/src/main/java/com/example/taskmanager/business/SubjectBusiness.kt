package com.example.taskmanager.business

import android.content.Context
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
            if (name.equals("") || description.equals("")) {
                throw ValidationException(context.getString(R.string.informar_campos))
            }
            if (db.subjectDao().isSubjectExistent(name)) {
                throw ValidationException(context.getString(R.string.disciplina_cadastrada))
            }

            val idUser = sharedPreferences.getPreferences("USERID")
            db.subjectDao().insert(Subject(name,description,idUser.toInt()))
        } catch (e: Exception) {
            throw e
        }
    }
}