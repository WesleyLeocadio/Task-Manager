package com.example.taskmanager.business

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.room.Room
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.User
import com.example.taskmanager.util.SecurityPreferences
import com.example.taskmanager.util.ValidationException

class UserBusiness(val context: Context) {

    private val db: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "task-bd").allowMainThreadQueries()
            .build()

    private val sharedPreferences: SecurityPreferences = SecurityPreferences(context)

    @Throws(ValidationException::class)
    fun insert(name: String, telephone: String, email: String, password: String) {

        try {
            if (name.equals("") || telephone.equals("") || email.equals("") || password.equals("")) {
                throw ValidationException(context.getString(R.string.informar_campos))
            }
            if (db.userDao().isEmailExistent(email)) {
                throw ValidationException(context.getString(R.string.email_cadastrado))

            }

            val id = db.userDao().insert(User(name, telephone, email, password))
            sharedPreferences.setPreferences("USER_ID", id.toString())
            sharedPreferences.setPreferences("USER_TELEFHONE", telephone)
            sharedPreferences.setPreferences("USER_EMAIL", email)
            sharedPreferences.setPreferences("USER_PASSWORD", password)



        } catch (e: Exception) {
            throw e

        }

        // Log.i("teste","${db.userDao().listAll().toString()}")
    }

    fun login(email: String, password: String): Boolean {
        val user = db.userDao().login(email, password)
        if (user != null) {
            sharedPreferences.setPreferences("USER_ID", user.id.toString())
            sharedPreferences.setPreferences("USER_TELEFHONE", user.telephone)
            sharedPreferences.setPreferences("USER_EMAIL", user.email)
            sharedPreferences.setPreferences("USER_PASSWORD", user.password)
            return true
        }
        return false
    }


}