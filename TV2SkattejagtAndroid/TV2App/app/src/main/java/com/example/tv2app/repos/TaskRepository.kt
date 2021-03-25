package com.example.tv2app.repos

import com.example.tv2app.models.TextTask
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

open class TaskRepository {

    //private lateinit var auth : FirebaseAuth
    private lateinit var ref : DatabaseReference

    fun dummyData(){

        val textTask1  = TextTask("TextTask1", 10, "Hvad er hovedstaden i Danmark", "Kantine","727SFBN", "", "København")
        val textTask2  = TextTask("TextTask2", 20, "3627+875", "Afdeling A","834683fsa", "", "4502")

        ref = FirebaseDatabase.getInstance().getReference("Tasks").child("TextTask")

        ref.child(textTask1.taskId).setValue(textTask1)
        ref.child(textTask2.taskId).setValue(textTask2)


    }

}