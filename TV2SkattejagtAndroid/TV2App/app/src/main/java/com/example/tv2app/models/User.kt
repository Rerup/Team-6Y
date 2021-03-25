package com.example.tv2app.models

class User() {
    constructor(totalPoints: Int, userIdDb: String, uniqueId: String, departmentId: String) : this() {
        this.totalPoints = totalPoints
        this.userIdDb = userIdDb
        this.uniqueId = uniqueId
        this.departmentId = departmentId
    }


    var totalPoints: Int = 0
    var userIdDb: String = ""
    var uniqueId: String = ""
    var departmentId: String = ""



}