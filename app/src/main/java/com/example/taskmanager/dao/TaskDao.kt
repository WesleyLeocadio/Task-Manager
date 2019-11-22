package com.example.taskmanager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskmanager.domain.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun listAll(): List<Task>

    @Query("SELECT * FROM task  WHERE  complete=:complete")
    fun listAllDone(complete:Int): List<Task>

    @Insert
    fun insert(book: Task): Long

    @Query("SELECT * FROM task  WHERE  name=:name")
    fun isTaskExistent(name:String):Boolean


    @Query("SELECT * FROM task  WHERE  id=:id")
    fun isTaskId(id:Int):Task
}