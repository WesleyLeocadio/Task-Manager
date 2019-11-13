package com.example.taskmanager.connectionBD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.dao.SubjectDao
import com.example.taskmanager.dao.UserDao
import com.example.taskmanager.domain.Subject
import com.example.taskmanager.domain.User

@Database(entities = [User::class,Subject::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun subjectDao(): SubjectDao



}