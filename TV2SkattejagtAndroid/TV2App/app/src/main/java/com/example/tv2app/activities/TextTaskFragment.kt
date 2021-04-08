package com.example.tv2app.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentProfileBinding
import com.example.tv2app.databinding.FragmentTextTaskBinding
import com.example.tv2app.models.TextTask
import com.example.tv2app.models.User
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TextTaskFragment : Fragment() {

    lateinit var auth: FirebaseAuth

    private val taskViewModel: TaskViewModel by activityViewModels()

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

        //TODO Jacob what is this?? Reference til Authenticatoren
        //auth = FirebaseAuth.getInstance()

        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)


        //TODO Jacobo er der bruge for dette? Yuis, når vi skal belønne brugeren med point
        //Track current User
        //val currentUser = auth.currentUser?.uid ?:""


        fun getCurrentTask(id: String) {
            db = FirebaseDatabase.getInstance()
            ref = db.reference.child("Tasks")
            ref.child(id).addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val task = snapshot
                    val currentTaskObject = task.getValue(TextTask::class.java)
                    binding.points = getString(R.string.point_amount, currentTaskObject?.point) ?: ""
                    binding.question = currentTaskObject?.description ?: ""

                    Log.i("DB READ", "point: ${binding.points}, question: ${binding.question}")
                }

                override fun onCancelled(error: DatabaseError) {
                    //Handle the error
                    Log.i("DB READ", error.message)
                }
            })
        }

        getCurrentTask(taskViewModel.scannedTaskId.toString())

        //TODO Fun Tjek om input er rigtig fra brugeren og giv point, hvis rigtig

        return binding.root
    }

}