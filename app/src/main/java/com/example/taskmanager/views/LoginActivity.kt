package com.example.taskmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taskmanager.R
import com.example.taskmanager.business.UserBusiness
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var userBusiness: UserBusiness


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttons()
        userBusiness = UserBusiness(this)


    }


    private fun buttons() {
        btnEntrar.setOnClickListener {
            login()

        }

        btnSair.setOnClickListener { }

    }


    private fun login() {
        if (userBusiness.login(txtEmailLogin.text.toString(), txtPasswordLogin.text.toString())) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, getString(R.string.login_e_senha_incorreto), Toast.LENGTH_LONG)
                .show()
        }

    }
}
