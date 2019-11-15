package com.example.taskmanager.ui.subject

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskmanager.R
import com.example.taskmanager.business.SubjectBusiness
import com.example.taskmanager.business.UserBusiness
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_register_subject.*


class RegisterSubjectFragment : Fragment() {

    private lateinit var subjectBusiness: SubjectBusiness

    var dadosValidados:Boolean = false

    override fun onResume() {
        super.onResume()
        subjectBusiness = SubjectBusiness( this!!.getContext()!!)
        buttons()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    private fun buttons() {
        btnRegister.setOnClickListener {
           dadosValidados = validarFormulario()
           if (dadosValidados){
               subjectBusiness.insert(txtName.text.toString(), txtDescription.text.toString())
           }else{
               Toast.makeText(this!!.context, getString(R.string.dados_incorretos), Toast.LENGTH_LONG)
                   .show()
           }

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register_subject, container, false)
    }



    private fun validarFormulario():Boolean{
        //Regra de validação
        var retorno = false

        if (!TextUtils.isEmpty(txtName.text.toString())){
            retorno = true
        }else{
            txtName.setError("Por favor, insira um nome para a disciplina")
            txtName.requestFocus()
        }

        if (!TextUtils.isEmpty(txtDescription.text.toString())){
            retorno = true
        }else{
            txtDescription.setError("Por favor, insira uma descrição")
            txtDescription.requestFocus()
        }

        return retorno
    }
}
