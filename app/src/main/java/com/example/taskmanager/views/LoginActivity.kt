package com.example.taskmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.taskmanager.R
import com.example.taskmanager.business.UserBusiness
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.domain.Subject
import com.example.taskmanager.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var userBusiness: UserBusiness

    private lateinit var sharedPreferences: SecurityPreferences

    var dadosValidados:Boolean = false

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "task-bd"
        )
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Log.i("teste","${db.subjectDao().listAll()[2].name}")
        // db.subjectDao().insert(Subject("POO","Professora Laura",2))
        //db.subjectDao().insert(Subject("PDM","Professor Taniro",2))
       // Log.i("teste","${db.taskDao().listAll()[2].toString()}")


        userBusiness = UserBusiness(this)
        sharedPreferences= SecurityPreferences(this)
        verificarUsuarioLogado()
        buttons()


    }

    private fun verificarUsuarioLogado(){
        if(!sharedPreferences.getPreferences("USER_ID").equals("")&& !sharedPreferences.getPreferences("USER_EMAIL").equals("") ){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun buttons() {
        btnEntrar.setOnClickListener {
            dadosValidados = validarFormulario()
            if (dadosValidados){
                login()
            }else{
                Toast.makeText(this, getString(R.string.login_e_senha_incorreto), Toast.LENGTH_LONG)
                    .show()
            }
        }

        btnCriarConta.setOnClickListener {
            startActivity(Intent(this, RegisterUserActivity::class.java))
            finish()
        }

    }


    private fun login() {
        // trim() : remove os espaços dos caracteres da direita e da esquerda
        if (userBusiness.login(txtEmailLogin.text.toString().trim(), txtPasswordLogin.text.toString().trim())) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,getString(R.string.login_e_senha_incorreto),Toast.LENGTH_LONG).show()
        }
    }


    private fun validarFormulario():Boolean{
        //Regra de validação
        var retorno = false

        if (!TextUtils.isEmpty(txtEmailLogin.text.toString())){
            retorno = true
        }else{
            txtEmailLogin.setError("Insira um email por favor")
            txtEmailLogin.requestFocus()
        }

        if (!TextUtils.isEmpty(txtPasswordLogin.text.toString())){
            retorno = true
        }else{
            txtPasswordLogin.setError("Insira uma senha por favor")
            txtPasswordLogin.requestFocus()
        }

        return retorno
    }
}
