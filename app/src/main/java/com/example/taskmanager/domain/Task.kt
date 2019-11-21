package com.example.taskmanager.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Task (var name: String,
            var description: String,
            var dueDate: String,
            var complete: Int,
            @ForeignKey(
                entity = Subject::class,
                parentColumns = ["id"],
                childColumns = ["subject"],onDelete = ForeignKey.CASCADE,onUpdate = ForeignKey.CASCADE)
            var  subject: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}