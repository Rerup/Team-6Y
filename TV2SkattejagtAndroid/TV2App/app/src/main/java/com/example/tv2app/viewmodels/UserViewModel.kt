package com.example.tv2app.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.tv2app.repos.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {


    fun createUser(email : String, password : String, department : String, id : String, context: Context){
        userRepository.createUser(email, password, department, id, context)

    }

    fun loginUser(email: String, password: String, context: Context){
        userRepository.logInUser(email, password, context)
    }

    fun signOutUser(){
        userRepository.signOutUser()
    }


}