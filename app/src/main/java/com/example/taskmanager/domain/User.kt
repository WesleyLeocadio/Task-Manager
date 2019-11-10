package com.example.taskmanager.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (var name:String, var telephone:String, var email:String, var password:String){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}