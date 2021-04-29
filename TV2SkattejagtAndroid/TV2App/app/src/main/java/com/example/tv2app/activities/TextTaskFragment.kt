package com.example.tv2app.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentTextTaskBinding
import com.example.tv2app.services.QRServiceRepository
import com.example.tv2app.viewmodels.QRServiceViewModel
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TextTaskFragment : Fragment() {



    private val taskViewModel: TaskViewModel by sharedViewModel()
    private val userViewModel: UserViewModel by sharedViewModel()
    private val qrViewModel: QRServiceViewModel by sharedViewModel()



    private lateinit var binding: FragmentTextTaskBinding

    //Database Refs
    lateinit var auth: FirebaseAuth




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

        //Buttons
        //Checking if user answered correctly
        binding.answerButton.setOnClickListener{validateAnswer()}

        //Track current User
        //val currentUser = auth.currentUser?.uid ?:""

        //Populates view with the scanned task values


        return binding.root
    }



    private fun showTaskData(){

        //Get Current TextTask from the scanned QR Contents via Id.
        taskViewModel.getCurrentTask(qrViewModel.scannedTaskId)
        //Fetching the object
        val taskObject = taskViewModel.fetchTaskToView()

        binding.points = "${taskObject?.point} points på højkant!" ?: ""
        binding.question = taskObject?.description ?: ""

    }

    private fun validateAnswer(){
        val taskObject = taskViewModel.fetchTaskToView()

        if (binding.answerInput.text.toString() == taskObject?.solution ?:""){
            userViewModel.rewardUserPoints(taskObject?.point ?:0, auth.currentUser?.uid ?:"")
            Log.i("Validater", "Correct answer")
        }
        else {
            Log.i("Validater", "Wrong answer")
        }
        goToMainMenu()
    }

    private fun goToMainMenu(){
        //Måske et fragment der fortæller om man har svaret rigtigt eller forkert?
        findNavController().navigate(R.id.action_textTaskFragment_to_startFragment)

    }

}