package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.taskmanager.adapter.TaskAdapterRecycler
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.listener.TaskListener
import kotlinx.android.synthetic.main.activity_task_subject.*

class TaskSubjectActivity : AppCompatActivity() {
    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, "task-bd"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_subject)


        var params = intent.extras
        var idSubject = params?.getInt("id")

        ActionBack()

       // Log.i("teste","${idSubject}")
        var adapter = TaskAdapterRecycler(this, db.taskDao().listAllTaskSubject(idSubject!!.toInt()))
        recyclerviewTaskSubjectList.adapter = adapter
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerviewTaskSubjectList.layoutManager = layout


        recyclerviewTaskSubjectList.addOnItemTouchListener(
            TaskListener(
                this,
                recyclerviewTaskSubjectList,
                object : TaskListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                    }

                    override fun onItemLongClick(view: View, position: Int) {

                    }
                })
        )
    }

    private fun ActionBack(){
        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
    }
}
