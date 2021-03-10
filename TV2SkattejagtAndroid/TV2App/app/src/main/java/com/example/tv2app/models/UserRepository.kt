package com.example.tv2app.models

import android.content.Intent
import android.util.Log
import com.example.tv2app.activities.LoginActivity
import com.example.tv2app.activities.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class UserRepository(private val user : User ) {

    lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference


    fun createUser(email: String, password : String, department: String, id: String){
        auth.createUserWithEmailAndPassword(email, password)

                val newUserParams = User(totalPoints = 0, userIdDb = auth.currentUser.uid, departmentId = department, uniqueId = id)
                ref.setValue(newUserParams)
            }
    }

    fun registerUser(){

    }

    fun loginUser(){
        
    }

