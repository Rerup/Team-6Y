package com.example.tv2app.viewmodels

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.tv2app.activities.MenuActivity
import com.example.tv2app.repos.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {

    fun createUser(email : String, password : String, department : String, id : String){
        userRepository.createUser(email, password, department, id)

    }

    fun loginUser(email: String, password: String){
        userRepository.logInUser(email, password)
    }


}