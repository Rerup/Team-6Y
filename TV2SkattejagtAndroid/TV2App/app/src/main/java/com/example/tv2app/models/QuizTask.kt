package com.example.tv2app.models

import com.example.tv2app.interfaces.Task

class QuizTask(
        override var nameTask : String,
        override var point : Int,
        override var description : String,
        override var location : String,
        override var taskId : String,
        var option1 : String,
        var option2 : String,
        var option3 : String,
        var option4 : String,
        var solution : String ) : Task {

}