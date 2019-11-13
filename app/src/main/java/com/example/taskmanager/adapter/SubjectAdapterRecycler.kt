package com.example.taskmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.domain.User
import com.example.taskmanager.viewholder.SubjectViewHolder
import com.example.taskmanager.R

class SubjectAdapterRecycler(var c: Context, var users: List<User>) :
    RecyclerView.Adapter<SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.inflatersubject, parent, false)
        return SubjectViewHolder(view)

    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        var bookAtual = users.get(position)

        holder.name.text = bookAtual.name
        holder.telephone.text = bookAtual.telephone
    }


}