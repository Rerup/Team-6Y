package com.example.tv2app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.tv2app.repos.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {

    fun createUser(email : String, password : String, department : String, id : String){
        userRepository.createUser(email, password, department, id)

    }




}