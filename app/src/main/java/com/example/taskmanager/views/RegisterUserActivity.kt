package com.example.taskmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import androidx.room.Room
import com.example.taskmanager.business.UserBusiness
import com.example.taskmanager.util.ValidationException

import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {


    private lateinit var userBusiness: UserBusiness

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
        btnCancel.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun salveUser(){
        try {
            userBusiness.insert(txtName.text.toString(),txtNumber.text.toString(),txtEmail.text.toString(),txtPassword.text.toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }catch (e:ValidationException){
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(this,getString(R.string.erro_inesperado),Toast.LENGTH_LONG).show()

        }
        clearFields()
    }

    private fun clearFields(){
       txtName.setText("")
        txtNumber.setText("")
        txtEmail.setText("")
        txtPassword.setText("")
    }
}
