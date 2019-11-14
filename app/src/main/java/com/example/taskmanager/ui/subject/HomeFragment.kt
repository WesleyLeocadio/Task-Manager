package com.example.taskmanager.ui.subject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.taskmanager.R
import com.example.taskmanager.adapter.SubjectAdapterRecycler
import com.example.taskmanager.connectionBD.AppDatabase
import com.example.taskmanager.listener.SubjectListener
import kotlinx.android.synthetic.main.fragment_home2.*

class HomeFragment : Fragment() {
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


        var adapter = SubjectAdapterRecycler(this!!.getContext()!!,db.subjectDao().listAll() )
        recyclerview.adapter=adapter

        val layout = LinearLayoutManager(this!!.getContext()!!, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = layout


        recyclerview.addOnItemTouchListener(
            SubjectListener(
                this!!.getContext()!!,
                recyclerview,
                object : SubjectListener.OnItemClickListener {
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

        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

}
