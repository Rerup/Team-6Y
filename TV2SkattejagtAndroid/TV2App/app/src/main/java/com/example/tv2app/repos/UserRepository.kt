package com.example.tv2app.repos

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tv2app.activities.LoginActivity
import com.example.tv2app.activities.MenuActivity
import com.example.tv2app.activities.RegisterActivity
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

open class UserRepository {

    private lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference



    //Create an account with the params the user gave. Firebase saves Auth User to DB and User Object.
    fun createUser(email: String, password : String, department: String, id: String){

        //Authentication Instance.
        auth = FirebaseAuth.getInstance()

        //The path will be Users Node.
        ref = FirebaseDatabase.getInstance().getReference("Users")

        //Created Auth Account with Email and Password User provided.
        auth.createUserWithEmailAndPassword(email, password)

        val idDb = auth.currentUser.uid




        //Create the User object with these Params, standard is 0 point when account is created.
        val user = User(totalPoints = 0, userIdDb = idDb, departmentId = department, uniqueId = id)

        //Get Unique Identifier to Firebase. User will be seen as this Id in the Database.
        val dbKey = ref.push().key.toString()

        //Save object to this location and set the values of the object given by the user.
        ref.child(dbKey).setValue(user)


    }


    fun signOutUser(){

    }

     fun logInUser(email : String, password: String){

        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)

        //Sets current User
        val user = auth.currentUser

    }



}








