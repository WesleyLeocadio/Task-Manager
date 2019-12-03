package com.example.taskmanager.views

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanager.R
import com.example.taskmanager.business.TaskBusiness
import com.example.taskmanager.util.ValidationException
import kotlinx.android.synthetic.main.activity_task.*
import java.util.*

class TaskActivity : AppCompatActivity() {

    private lateinit var taskBusiness: TaskBusiness
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)


    var dadosValidados:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        buttons()
        taskBusiness = TaskBusiness(this)
        taskBusiness.SpinnerTeste(spinner)

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

        calendar.setOnClickListener {
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->
                txtDate.text = "${mDay}/${mMonth+1}/${mYear}"
            }, year, month, day)
            dpd.show()
        }

    }

    private fun validarFormulario():Boolean{
        //Regra de validação
        var retorno = false

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

    private fun ActionBack(){
        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun tituloAction(){
        val ab = supportActionBar
        ab!!.setTitle(R.string.cadastro_tarefas)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val nome = findViewById<TextView>(R.id.txtNameTask)
        val descricao = findViewById<TextView>(R.id.txtDescriptionTask)
        val data = findViewById<TextView>(R.id.txtDate)

        val userText:CharSequence = nome.text
        val descText:CharSequence = descricao.text
        val dateText:CharSequence = data.text
        outState.putCharSequence("nameSaved",userText)
        outState.putCharSequence("desSaved",descText)
        outState.putCharSequence("dateSaved",dateText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val nome = findViewById<TextView>(R.id.txtNameTask)
        val descricao = findViewById<TextView>(R.id.txtDescriptionTask)
        val data = findViewById<TextView>(R.id.txtDate)

        val userText: CharSequence? = savedInstanceState.getCharSequence("nameSaved")
        val descText: CharSequence? = savedInstanceState.getCharSequence("desSaved")
        val dataText: CharSequence? = savedInstanceState.getCharSequence("dateSaved")

        nome.text = userText
        descricao.text = descText
        data.text = dataText
    }
}
