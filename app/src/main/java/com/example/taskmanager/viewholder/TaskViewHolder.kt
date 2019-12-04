package com.example.taskmanager.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R


class TaskViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var description: TextView
    var dueData: TextView
    var priority:  ImageView
    var image: ImageView
    var imageDelete:ImageView
    var imageEdit:ImageView


    init {
        description = v.findViewById(R.id.textInflatDescription)
        dueData = v.findViewById(R.id.textInfDueData)
        priority = v.findViewById(R.id.imageTaskPrioridade)
        image = v.findViewById(R.id.imageTask)
        imageEdit = v.findViewById(R.id.imageTaskEdit)
        imageDelete= v.findViewById(R.id.imageTaskDelete)
    }
}