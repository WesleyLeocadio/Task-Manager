package com.example.taskmanager.util

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences (c: Context){
    private val sharedPreferences:SharedPreferences=c.getSharedPreferences("application",Context.MODE_PRIVATE)

    fun setPreferences(key:String, value: String){sharedPreferences.edit().putString(key,"") }

    fun getPreferences(key: String){sharedPreferences.getString(key,"")}

    fun removePreferences(key: String){sharedPreferences.edit().remove(key)
    }
}