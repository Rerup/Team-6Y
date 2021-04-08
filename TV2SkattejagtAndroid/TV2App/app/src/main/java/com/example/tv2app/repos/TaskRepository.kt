package com.example.tv2app.repos

import android.util.Log
import com.example.tv2app.models.QuizTask
import com.example.tv2app.models.TextTask
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

open class TaskRepository {


    private lateinit var ref : DatabaseReference
    private lateinit var db : FirebaseDatabase

    fun dummyData(){

        val textTask1  = TextTask("TextTask1", 10, "Hvad er hovedstaden i Danmark", "Kantine","727SFBN", "", "København")
        val textTask2  = TextTask("TextTask2", 20, "3627+875", "Afdeling A","834683fsa", "", "4502")
        val quizTask1 = QuizTask("QuizTask1", 15, "Hvor gammel er Mads?", "Hovedindgangen", "572859fb", "12", "30", "22", "19", "22")

        ref = FirebaseDatabase.getInstance().getReference("Tasks").child("TextTask")

        ref.child(textTask1.taskId).setValue(textTask1)
        ref.child(textTask2.taskId).setValue(textTask2)

        ref = FirebaseDatabase.getInstance().getReference("Tasks").child("QuizTask")
        ref.child(quizTask1.taskId).setValue(quizTask1)


    }


    fun getTypeTask(id : String) : String {

        //TODO FIX THIS SHIT
        var type = ""

        db = FirebaseDatabase.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("Tasks")

        val query = ref.child(id).limitToFirst(1).get()

        if (query.javaClass.simpleName == "TextTask"){
            type = "TextTask"
        }

        else if (query.javaClass.simpleName == "QuizTask"){
            type = "PhotoTask"
        }

        return type

    }


}