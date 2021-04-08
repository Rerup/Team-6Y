package com.example.tv2app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tv2app.repos.TaskRepository

class TaskViewModel(private val taskRepository : TaskRepository) : ViewModel() {

    //private val _scannedTaskId = MutableLiveData<String>()
    //val scannedTaskId : LiveData<String> = _scannedTaskId

     var _scannedTaskId : String = ""
     var _scannedQRContents : String = ""


    fun dummyData(){
        taskRepository.dummyData()
    }

    fun getTypeTask(id : String) : String{

        return taskRepository.getTypeTask(id)
    }

        fun saveQRContent(qr : String) {
            _scannedQRContents = qr
    }

    fun splitQRCode(qrContents: String) : String {

        val split = qrContents.split(",")
        _scannedTaskId = split[0]
        val taskType = split[1]

        return taskType

    }
}