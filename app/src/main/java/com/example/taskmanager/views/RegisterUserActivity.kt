package com.example.taskmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.taskmanager.R
import com.example.taskmanager.connectionBD.AppDatabase
import androidx.room.Room
import com.example.taskmanager.business.UserBusiness
import com.example.taskmanager.util.ValidationException
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {


    private lateinit var userBusiness: UserBusiness

    var dadosValidados:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)


        buttons()

        userBusiness= UserBusiness(this)

    }

    private fun buttons(){
        btnRegister.setOnClickListener {
            dadosValidados = validarFormulario()
            if (dadosValidados){
                salveUser()
            }else{
                Toast.makeText(this, getString(R.string.campos_preenchidos), Toast.LENGTH_LONG)
                    .show()
            }

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

    private fun validarFormulario():Boolean{
        //Regra de validação
        var retorno = false

        if (!TextUtils.isEmpty(txtName.text.toString())){
            retorno = true
        }else{
            txtName.setError("Insira um nome por favor")
            txtName.requestFocus()
        }

        if (!TextUtils.isEmpty(txtNumber.text.toString())){
            retorno = true
        }else{
            txtNumber.setError("Insira um número por favor")
            txtNumber.requestFocus()
        }

        if (!TextUtils.isEmpty(txtEmail.text.toString())){
            retorno = true
        }else{
            txtEmail.setError("Insira um email por favor")
            txtEmail.requestFocus()
        }

        if (!TextUtils.isEmpty(txtPassword.text.toString())){
            retorno = true
        }else{
            txtPassword.setError("Insira um password por favor")
            txtPassword.requestFocus()
        }

        return retorno
    }
}
