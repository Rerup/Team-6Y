package com.example.tv2app.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.tv2app.repos.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {

    //TODO LiveData

    fun createUser(email : String, password : String, department : String, id : String){
        userRepository.createUser(email, password, department, id)

    }

    fun loginUser(email: String, password: String){
        userRepository.logInUser(email, password)
    }

    fun signOutUser(){
        userRepository.signOutUser()
    }

    fun userListener(){
        userRepository.userListener()
    }


}