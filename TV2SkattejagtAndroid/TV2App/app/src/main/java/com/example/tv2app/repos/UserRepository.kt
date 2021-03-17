package com.example.tv2app.repos

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.tv2app.activities.LoginActivity
import com.example.tv2app.activities.MainActivity
import com.example.tv2app.activities.RegisterActivity
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.coroutines.coroutineContext

open class UserRepository {

    private lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference



    //Create an account with the params the user gave. Firebase saves Auth User to DB and User Object.
    fun createUser(email: String, password : String, department: String, id: String, context: Context){

        //Authentication Instance.
        auth = FirebaseAuth.getInstance()

        //The path will be Users Node.
        ref = FirebaseDatabase.getInstance().getReference("Users")

        //Created Auth Account with Email and Password User provided.
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(){
            task -> if (task.isSuccessful){

            //Get Unique Identifier to Firebase. User will be seen as this Id in the Database.
            val dbKey = ref.push().key.toString()

            val idDb = auth.currentUser?.uid

            //Create the User object with these Params, standard is 0 point when account is created.
            val user = User(totalPoints = 0, userIdDb = idDb!!, departmentId = department, uniqueId = id)

            //Save object to this location and set the values of the object given by the user.
            ref.child(dbKey).setValue(user)

            val intent = Intent(context, LoginActivity::class.java)
            startActivity(context,intent,null)
            Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show()

        }
            //Throw exception
            else{
                Log.i("AUTH", "Failed to create account" + task.exception)

        }

        }

    }

    fun signOutUser(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
    }

     fun logInUser(email : String, password: String, context: Context){

        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){
            task -> if (task.isSuccessful){

            val intent = Intent(context, MainActivity::class.java)
            startActivity(context,intent, null)
            Toast.makeText(context, "Signing in", Toast.LENGTH_SHORT).show()
        }
            else{
                Log.i("AUTH", "Failed to Sign in" + task.exception)
                Toast.makeText(context, "Failed, wrong password", Toast.LENGTH_SHORT).show()
            }
        }

    }



}








