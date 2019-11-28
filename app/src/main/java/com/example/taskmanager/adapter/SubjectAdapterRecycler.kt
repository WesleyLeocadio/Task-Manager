package com.example.taskmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.domain.User
import com.example.taskmanager.viewholder.SubjectViewHolder
import com.example.taskmanager.R
import com.example.taskmanager.domain.Subject

class SubjectAdapterRecycler(var c: Context, var subjects: List<Subject>) :
    RecyclerView.Adapter<SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.inflatersubject, parent, false)
        return SubjectViewHolder(view)

    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        var subjectAtual = subjects.get(position)

        holder.name.text = subjectAtual.name
        holder.description.text = subjectAtual.description
    }

    fun id(id: Int): Int {
        return subjects.get(id).id
    }
}