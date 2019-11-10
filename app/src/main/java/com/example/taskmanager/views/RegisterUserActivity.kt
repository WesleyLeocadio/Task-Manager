package com.example.taskmanager.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import androidx.room.Room
import com.example.taskmanager.business.UserBusiness

import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {


private lateinit var  userBusiness:UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)


        buttons()

        userBusiness= UserBusiness(this)

    }

    private fun buttons(){
        btnRegister.setOnClickListener {
            salveUser()
        }
        btnCancel.setOnClickListener {  }

    }

    private fun salveUser(){
        val name=txtName.text.toString()
        val telephone=txtNumber.text.toString()
        val email=txtEmail.text.toString()
        val password=txtPassword.text.toString()
        userBusiness.insert(name,telephone,email,password)
    }
}
