package com.example.taskmanager.business

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.User
import com.example.taskmanager.util.ValidationException

class UserBusiness(val context: Context) {

    private val db: AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "task-bd").allowMainThreadQueries()
            .build()

    fun insert(name: String, telephone: String, email: String, password: String) {

        try {
            if (name.equals("") || telephone.equals("") || email.equals("") || password.equals("")) {
                throw ValidationException("Informe todos os campos!")
            }
            val user = User(name, telephone, email, password)
            val userId = db.userDao().insert(user)
        } catch (e:Exception) {
            throw e

        }

        // Log.i("teste","${db.userDao().listAll().toString()}")
    }


}