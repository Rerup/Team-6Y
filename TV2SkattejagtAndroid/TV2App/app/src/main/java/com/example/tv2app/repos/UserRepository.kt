package com.example.tv2app.repos

import android.util.Log
import com.example.tv2app.activities.LoginActivity
import com.example.tv2app.activities.RegisterActivity
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

open class UserRepository {

    private lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference

    fun createUser(email: String, password : String, department: String, id: String){

        auth = FirebaseAuth.getInstance()

        ref = FirebaseDatabase.getInstance().getReference("Users")

        auth.createUserWithEmailAndPassword(email, password)

        val dbId = auth.currentUser.uid

        val user = User(totalPoints = 0, userIdDb = dbId, departmentId = department, uniqueId = id)
        val dbKey = ref.push().key.toString()
        ref.child(dbKey).setValue(user)
        ref.setValue(user)
    }
}

fun signOutUser(){

}

fun loginUser(){

}
