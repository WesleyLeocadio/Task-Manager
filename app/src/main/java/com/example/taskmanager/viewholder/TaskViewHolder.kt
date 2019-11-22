package com.example.taskmanager.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R


class TaskViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var description: TextView
    var dueData: TextView
    var priority: TextView
    var image: ImageView


    init {
        description = v.findViewById(R.id.textInflatDescription)
        dueData = v.findViewById(R.id.textInfDueData)
        priority = v.findViewById(R.id.textInfPriority)
        image = v.findViewById(R.id.imageTask)


    }
}