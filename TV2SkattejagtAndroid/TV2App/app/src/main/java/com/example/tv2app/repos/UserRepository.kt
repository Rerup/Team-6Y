package com.example.tv2app.repos

import android.util.Log
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

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
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(){
            task -> if (task.isSuccessful){

            //Get Unique Identifier to Firebase. User will be seen as this Id in the Database.
            val idDb = auth.currentUser?.uid ?:""

            //Create the User object with these Params, standard is 0 point when account is created.
            val user = User(totalPoints = 0, userIdDb = idDb, departmentId = department, uniqueId = id)

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


   /* fun getUserById(){
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?:""
        ref = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        val userListener = object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.value as User
                Log.i("READ USER", user.userIdDb)
                //val userPoints = snapshot.child("totalPoints").value as User
                //val department = snapshot.child("departmentId").value as User
                //val jobGroup = snapshot.child("jobGroup").value as User
                //val email = snapshot.child("email").value as User
            }

            //Handle DB Error
            override fun onCancelled(error: DatabaseError) {
                Log.i("DBRead", error.message)
            }

        }
        ref.addValueEventListener(userListener)

    }*/

    fun getAllUsers(){
        //TODO Lav logik

    }


}








