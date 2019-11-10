package com.example.taskmanager.connectionBD

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.dao.UserDao
import com.example.taskmanager.domain.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao



}