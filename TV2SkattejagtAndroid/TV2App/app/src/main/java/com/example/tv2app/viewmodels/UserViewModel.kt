package com.example.tv2app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tv2app.models.User
import com.example.tv2app.repos.UserRepository

class UserViewModel(private val userRepository : UserRepository) : ViewModel() {

     var userObject : User? = User(null, null, null, null, null, null)
     var userList : ArrayList<User?> = ArrayList()


    fun createUser(email: String, password: String, department: String, id: String, fullName : String, job : String) {
        userRepository.createUser(email, password, department, id, fullName, job)

    }

    fun loginUser(email: String, password: String) {
        userRepository.logInUser(email, password)
    }

    fun signOutUser() {
        userRepository.signOutUser()
    }

     fun getCurrentUser(id : String) {
        userRepository.getCurrentUser(id)
    }

    fun fetchUserToView() : User? {
        userObject = userRepository.userObject

        return userObject
    }

    fun rewardUserPoints(points : Int, id : String){
        userRepository.rewardUserPoints(points, id)
    }

    fun inflateLeaderboard() : ArrayList<User?> {
        userRepository.inflateLeaderboard()

        if (userRepository.userList.size == 0){
            Log.i("DB READ", "List Empty")

        }
        else {
            userList = userRepository.userList
            return userList
        }
        return userList
    }

    fun findUserIndexInLeaderboard(array : ArrayList<User?>, item : User?) : Int {
        return userRepository.findUserIndexInLeaderboard(array, item)
    }



}