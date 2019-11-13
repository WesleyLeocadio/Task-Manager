package com.example.taskmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

       // Log.i("teste","${db.subjectDao().listAll()[0].name}")

        //db.subjectDao().insert(Subject("show","show",2))

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
            login()

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
            clearFields()
        } else {
            Toast.makeText(this, getString(R.string.login_e_senha_incorreto), Toast.LENGTH_LONG)
                .show()
        }

    }
     private fun clearFields(){
        txtEmailLogin.setText("")
         txtPasswordLogin.setText("")
    }
}
