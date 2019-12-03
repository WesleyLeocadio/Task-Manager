package com.example.taskmanager.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
class Subject(var name: String,
              var description: String,
              @ForeignKey(
              entity = User::class,
              parentColumns = ["id"],
              childColumns = ["user"],onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)
              var  user: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}