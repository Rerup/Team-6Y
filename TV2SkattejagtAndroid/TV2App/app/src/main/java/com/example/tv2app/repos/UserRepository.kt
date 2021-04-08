package com.example.tv2app.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.sync.Mutex

open class UserRepository {

    private lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference
    private lateinit var db : FirebaseDatabase
    private lateinit var allUsers : MutableList<User?>



    //Create an account with the params the user gave. Firebase saves Auth User to DB and User Object.
    fun createUser(email: String, password : String, department: String, id: String, fullName: String, job : String){

        //Authentication Instance.
        auth = FirebaseAuth.getInstance()

        //The path will be Users Node.
        ref = FirebaseDatabase.getInstance().getReference("Users")

        //Created Auth Account with Email and Password User provided.
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(){
            task -> if (task.isSuccessful){

            //Get Unique Identifier to Firebase. User will be seen as this Id in the Database.
            val idDb = auth.currentUser?.uid ?:""

            //Create the User object with these Params, standard is 0 point when account is created.
            val user = User(totalPoints = 0, departmentId = department, uniqueId = id, email = email, fullName = fullName, job = job)

            //Save object to this location and set the values of the object given by the user.
            ref.child(idDb).setValue(user)

        }
            //Throw exception
            else{
                Log.i("AUTH", "Failed to create account " + task.exception)

            }

        }

    }

    fun signOutUser(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
    }

    fun logInUser(email : String, password: String){

        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(){

            task -> if (task.isSuccessful){

            Log.i("AUTH", "Logged In Correctly")

        }
            else{
                Log.i("AUTH", "Failed to Sign in" + task.exception)
            }
        }

    }


}












