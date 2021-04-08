package com.example.tv2app.models

import com.example.tv2app.interfaces.Task

class TextTask() : Task {
    constructor(nameTask: String, point: Int, description: String, location: String, taskId: String, answer : String, solution : String) : this() {
        this.nameTask = nameTask
        this.point = point
        this.description = description
        this.location = location
        this.taskId = taskId
        this.answer = answer
        this.solution = solution
    }

    override var nameTask: String = ""
    override var point: Int = 0
    override var description: String = ""
    override var location: String = ""
    override var taskId: String = ""
    var answer : String = ""
    var solution : String = ""
}


