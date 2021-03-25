package com.example.tv2app.repos

import android.util.Log
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

open class UserRepository {

    private lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference
    private lateinit var db : FirebaseDatabase


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

    //TODO Denne skal i View, hvor der er brug for den samt linkes til View Elementer, denne kan bruges til Leaderboard med et RecyclerView
    fun userListener(){

        //Firebase Instance and Reference
        db = FirebaseDatabase.getInstance()
        ref = db.reference

        ref.child("Users").addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                //Get all children at reference which are Users.
                val children = snapshot.children
                //Iterate through all of the children/Users.
                for (child : DataSnapshot in children){
                    //Set value to object of user

                    //TODO Implement more properties to use in View
                    val value = child.getValue(User::class.java)
                    val totalPoints = value?.totalPoints ?:""
                    val department = value?.departmentId ?:""
                    val id = value?.userIdDb ?:""
                    val tv2id = value?.uniqueId ?:""
                    //Testing
                    Log.i("DB READ", "Id: $id points: $totalPoints department: $department tv2Id: $tv2id")

                }
                    // Set to UI Elements here.
                }
            //Handle the error
            override fun onCancelled(error: DatabaseError) {
                Log.i("DB READ", error.message)
            }

        })


    }

    }












