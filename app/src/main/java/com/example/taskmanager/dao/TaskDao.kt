package com.example.taskmanager.dao

import androidx.room.*
import com.example.taskmanager.domain.Task
import java.util.ArrayList

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun listAll(): List<Task>

    @Query("SELECT * FROM task  WHERE  complete=:complete")
    fun listAllDone(complete:Int): List<Task>

    @Query("SELECT * FROM task  WHERE  subject=:id")
    fun listAllTaskSubject(id:Int): List<Task>

    @Insert
    fun insert(book: Task): Long



    @Delete
    fun deletar(book: Task): Int

    @Query("SELECT * FROM task  WHERE  name=:name")
    fun isTaskExistent(name:String):Boolean

    @Update
    fun atualizar(book: Task): Int



    @Query("SELECT * FROM task  WHERE  id=:id")
    fun isTaskId(id:Int):Task
}