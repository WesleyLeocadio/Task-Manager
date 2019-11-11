package com.example.taskmanager.dao

import androidx.room.*
import com.example.taskmanager.domain.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun listAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun findById(id: Long): User

    @Query("SELECT * FROM user WHERE name = :name")
    fun findByName (name: String) : User

    @Query("DELETE FROM user")
    fun deleteAll(): Int

    @Insert
    fun inserirAll(vararg l: User): LongArray


    @Insert
    fun insert(book: User): Long

    @Delete
    fun deletar(book: User): Int

    @Update
    fun atualizar(book: User): Int


    @Query("SELECT * FROM user")
    fun listAll1(): MutableList<User>


    @Query("SELECT * FROM user  WHERE  email=:email")
    fun isEmailExistent(email:String):Boolean

    @Query("SELECT * FROM user  WHERE  email=:email AND password=:password")
    fun login(email: String,password:String):User





}