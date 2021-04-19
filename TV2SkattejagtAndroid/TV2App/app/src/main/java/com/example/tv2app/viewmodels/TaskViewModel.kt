package com.example.tv2app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tv2app.models.TextTask
import com.example.tv2app.repos.TaskRepository

class TaskViewModel(private val taskRepository : TaskRepository) : ViewModel() {

    //private val _scannedTaskId = MutableLiveData<String>()
    //val scannedTaskId : LiveData<String> = _scannedTaskId

    private var textTaskObject : TextTask? = TextTask(null, null,  null, null, null, null, null )


    fun dummyData(){
        taskRepository.dummyData()
    }

    fun fetchTaskToView() : TextTask? {
        textTaskObject = taskRepository.textTaskObject
        return  textTaskObject

    }

    fun getCurrentTask(scannedTaskId : String){
        taskRepository.getCurrentTask(scannedTaskId)


    }


}