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

    var userObject : User? = User(null, null,null,null,null,null)

    //Companion Object


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

    fun getCurrentUser(id : String) {
        db = FirebaseDatabase.getInstance()
        ref = db.reference.child("Users")
        ref.child(id).addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                val user = snapshot
                val currentUserObject = user.getValue(User::class.java)

                //Variable used in ViewModel so that it can be used in view.
                userObject = currentUserObject

                if (userObject == null){

                    Log.i("DB READ", "User Object Variable is currently null, async call.")
                }
                else {
                    fetchUserToView(userObject)
                }

                //Check to see if we are getting values.
                val email = currentUserObject?.email ?:""
                val uniqueId = currentUserObject?.uniqueId ?:""
                val department = currentUserObject?.departmentId ?:""
                val points = currentUserObject?.totalPoints ?:0
                val fullName = currentUserObject?.fullName ?:""
                val job = currentUserObject?.job ?:""


                Log.i("DB READ", "email: $email, tv2id: $uniqueId  department: $department  totalPoints: $points, Name: $fullName, Job: $job")
            }

            override fun onCancelled(error: DatabaseError) {
                //Handle the error
                Log.i("DB READ", error.message)
            }
        })

    }

    fun fetchUserToView(user : User?) : User? {
        return user
    }

    fun rewardUserPoints(points : Int, id : String) {
        db = FirebaseDatabase.getInstance()
        ref = db.reference.child("Users")
            ref.child(id).addListenerForSingleValueEvent(object : ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {
                    val currentUserObject = snapshot.getValue(User::class.java)
                    currentUserObject?.totalPoints?.plus(points)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("DB READ", error.message)
                }
            })

    }

}












