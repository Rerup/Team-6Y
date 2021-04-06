package com.example.tv2app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tv2app.repos.TaskRepository

class TaskViewModel(private val taskRepository : TaskRepository) : ViewModel() {

    private val _scannedTaskId = MutableLiveData<String>()
    val scannedTaskId : LiveData<String> = _scannedTaskId


    fun dummyData(){
        taskRepository.dummyData()
    }

    fun getTypeTask(id : String) : String{

        return taskRepository.getTypeTask(id)
    }

    fun setScannedTaskId(id : String) {
        _scannedTaskId.value = id
    }
}