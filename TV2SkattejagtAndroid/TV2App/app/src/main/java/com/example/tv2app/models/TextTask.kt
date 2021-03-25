package com.example.tv2app.models

import com.example.tv2app.interfaces.Task

class TextTask(override var nameTask: String,
               override var point: Int,
               override var description: String,
               override var location: String,
               override var taskId: String,
               var answer : String,
               var solution : String) : Task {



}