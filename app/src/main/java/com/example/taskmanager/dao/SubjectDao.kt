package com.example.taskmanager.dao

import androidx.room.*
import com.example.taskmanager.domain.Subject
import com.example.taskmanager.domain.User

@Dao
interface SubjectDao {

    @Query("SELECT * FROM subject")
    fun listAll(): List<Subject>

    @Insert
    fun insert(book: Subject): Long

    @Query("SELECT * FROM subject  WHERE  name=:name")
    fun isSubjectExistent(name:String):Boolean


    @Query("SELECT * FROM subject  WHERE  id=:id")
    fun isSubjectId(id:Int):Subject






}