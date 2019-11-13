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







}