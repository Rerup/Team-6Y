package com.example.tv2app.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentProfileBinding
import com.example.tv2app.databinding.FragmentTextTaskBinding
import com.example.tv2app.models.TextTask
import com.example.tv2app.models.User
import com.example.tv2app.services.QRService
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TextTaskFragment : Fragment() {

    lateinit var auth: FirebaseAuth

    private val taskViewModel: TaskViewModel by sharedViewModel()
    private lateinit var _qrService : QRService

    private lateinit var binding: FragmentTextTaskBinding
    private lateinit var db: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        //auth = FirebaseAuth.getInstance()

        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_text_task, container, false)

        showTaskData()

        //Track current User
        //val currentUser = auth.currentUser?.uid ?:""



        return binding.root
    }

    private fun showTaskData(){
        //Reference to QR Service
        val qrService = _qrService
        //Get Current TextTask from the scanned QR Contents via Id.
        taskViewModel.getCurrentTask(qrService._scannedTaskId)
        //Fetching the object
        val taskObject = taskViewModel.fetchTaskToView()

        binding.points = "${taskObject?.point} points på højkant!" ?: ""
        binding.question = taskObject?.description ?: ""

    }

    //TODO Fun Tjek om input er rigtig fra brugeren og giv point, hvis rigtig

}