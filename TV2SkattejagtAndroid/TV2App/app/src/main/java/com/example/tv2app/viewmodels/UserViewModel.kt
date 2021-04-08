package com.example.tv2app.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tv2app.models.User
import com.example.tv2app.repos.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {


    fun createUser(email: String, password: String, department: String, id: String, fullName : String, job : String) {
        userRepository.createUser(email, password, department, id, fullName, job)

    }

    fun loginUser(email: String, password: String) {
        userRepository.logInUser(email, password)
    }

    fun signOutUser() {
        userRepository.signOutUser()
    }

}