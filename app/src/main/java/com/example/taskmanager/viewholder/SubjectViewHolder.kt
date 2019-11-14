package com.example.taskmanager.viewholder

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R


class SubjectViewHolder  (v: View) : RecyclerView.ViewHolder(v){
    var name:TextView
    var description:TextView


    init {
        Log.i("HOLDER", "Fazendo buscas por id...")
        name = v.findViewById(R.id.textInflaterName)
        description = v.findViewById(R.id.textInfDescription)



    }
}