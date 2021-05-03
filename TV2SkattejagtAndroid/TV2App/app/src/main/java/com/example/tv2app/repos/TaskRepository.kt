package com.example.tv2app.repos

import android.util.Log
import com.example.tv2app.models.QuizTask
import com.example.tv2app.models.TextTask
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

open class TaskRepository {


    private lateinit var ref : DatabaseReference
    private lateinit var db : FirebaseDatabase

    var textTaskObject : TextTask? = TextTask(null, null, null, null, null, null,null)

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

    fun getCurrentTask(id: String) {
        db = FirebaseDatabase.getInstance()
        ref = db.reference.child("Tasks").child("TextTask")
        ref.child(id).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                //Fetch TextTask Object via snapshot
                val task = snapshot
                val currentTaskObject = task.getValue(TextTask::class.java)

                //Populate variable to fetched TextTask Object
                textTaskObject = currentTaskObject
                if (textTaskObject == null){

                    Log.i("DB READ", "TextTask Object is currently null, async call.")
                }

                //Send object to ViewModel
                fetchTaskToView(textTaskObject)

                //Logging fetched data
                val points = "${currentTaskObject?.point} points på højkant!" ?: ""
                val question = currentTaskObject?.description ?: ""
                Log.i("DB READ", "point: $points, question: $question")

            }

            override fun onCancelled(error: DatabaseError) {
                //Handle the error
                Log.i("DB READ", error.message)
            }

        })

    }

    fun fetchTaskToView(textTask : TextTask?) : TextTask? {
        return textTask
    }




}