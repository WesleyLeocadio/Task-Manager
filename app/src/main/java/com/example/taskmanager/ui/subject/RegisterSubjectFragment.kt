package com.example.taskmanager.ui.subject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskmanager.R
import com.example.taskmanager.business.SubjectBusiness
import com.example.taskmanager.business.UserBusiness
import kotlinx.android.synthetic.main.fragment_register_subject.*


class RegisterSubjectFragment : Fragment() {

    private lateinit var subjectBusiness: SubjectBusiness

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
           subjectBusiness.insert(txtName.text.toString(), txtDescription.text.toString())
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register_subject, container, false)
    }


}
