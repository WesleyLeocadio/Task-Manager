package com.example.taskmanager.ui.task

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room

import com.example.taskmanager.R
import com.example.taskmanager.adapter.TaskAdapterRecycler
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.listener.TaskListener
import kotlinx.android.synthetic.main.fragment_task.*


class TaskFragment(val x:Int) : Fragment() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this!!.getContext()!!,
            AppDatabase::class.java, "task-bd"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onResume() {
        super.onResume()


        var adapter = TaskAdapterRecycler(this!!.getContext()!!, db.taskDao().listAllDone(x))
        recyclerviewTaskList.adapter = adapter

        val layout = LinearLayoutManager(this!!.getContext()!!, LinearLayoutManager.VERTICAL, false)
        recyclerviewTaskList.layoutManager = layout


        recyclerviewTaskList.addOnItemTouchListener(
            TaskListener(
                this!!.getContext()!!,
                recyclerviewTaskList,
                object : TaskListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {


                    }

                    override fun onItemLongClick(view: View, position: Int) {

                    }
                })
        )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false)
    }


}
