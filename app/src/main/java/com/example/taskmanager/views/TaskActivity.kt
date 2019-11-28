package com.example.taskmanager.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.view.get
import com.example.taskmanager.R
import com.example.taskmanager.business.TaskBusiness
import com.example.taskmanager.util.ValidationException
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_task.*
import kotlin.properties.Delegates

class TaskActivity : AppCompatActivity() {

    private lateinit var taskBusiness: TaskBusiness


    var dadosValidados:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        buttons()
        taskBusiness = TaskBusiness(this)
        taskBusiness.SpinnerTeste(spinner)

        mascaraDate()
        ActionBack()
        tituloAction()

    }

    private fun buttons(){
        btnRegisterTask.setOnClickListener {
            dadosValidados = validarFormulario()
            if (dadosValidados){
                salveTask()
            }else{
                Toast.makeText(this, getString(R.string.campos_preenchidos), Toast.LENGTH_LONG)
                    .show()
            }

        }
        btnCancelTask.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun validarFormulario():Boolean{
        //Regra de validação
        var retorno = false


        //para o spinner
        /*if (!TextUtils.isEmpty(txtEmail.text.toString())){
            retorno = true
        }else{
            txtEmail.setError("Insira um email por favor")
            txtEmail.requestFocus()
        } */

        if (!TextUtils.isEmpty(txtNameTask.text.toString())){
            retorno = true
        }else{
            txtNameTask.setError("Por favor, insira um nome")
            txtNameTask.requestFocus()
        }

        if (!TextUtils.isEmpty(txtDescriptionTask.text.toString())){
            retorno = true
        }else{
            txtDescriptionTask.setError("Por favor, insira uma descrição")
            txtDescriptionTask.requestFocus()
        }

        if (!TextUtils.isEmpty(txtDate.text.toString())){
            retorno = true
        }else{
            txtDate.setError("Por favor, insira uma data válida")
            txtDate.requestFocus()
        }


        return retorno
    }

    private fun salveTask(){
        try {
            taskBusiness.insert(txtNameTask.text.toString(),txtDescriptionTask.text.toString(),txtDate.text.toString(),0)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }catch (e: ValidationException){
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }catch (e:Exception){
            Toast.makeText(this,getString(R.string.erro_inesperado),Toast.LENGTH_LONG).show()

        }
        clearFields()
    }


    private fun clearFields(){
        txtNameTask.setText("")
        txtDescriptionTask.setText("")
        txtDate.setText("")
    }

    private fun mascaraDate(){
        var smf = SimpleMaskFormatter("NN/NN/NNNN")
        var mtw = MaskTextWatcher(txtDate,smf)
        txtDate.addTextChangedListener(mtw)
    }

    private fun ActionBack(){
        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun tituloAction(){
        val ab = supportActionBar
        ab!!.setTitle(R.string.cadastro_tarefas)
    }
}
