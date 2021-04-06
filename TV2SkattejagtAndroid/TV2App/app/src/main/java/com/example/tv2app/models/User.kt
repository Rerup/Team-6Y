package com.example.tv2app.models

class User() {
    constructor(totalPoints: Int?, uniqueId: String?, departmentId: String?, email : String?, fullName : String?) : this() {
        this.totalPoints = totalPoints ?:0
        this.uniqueId = uniqueId ?:""
        this.departmentId = departmentId ?:""
        this.email = email ?:""
        this.fullName  = fullName ?:""
    }

    var totalPoints: Int = 0
    var uniqueId: String = ""
    var departmentId: String = ""
    var email: String = ""
    var fullName : String = ""


}