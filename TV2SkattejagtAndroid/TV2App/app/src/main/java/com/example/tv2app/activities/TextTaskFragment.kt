package com.example.tv2app.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentProfileBinding
import com.example.tv2app.databinding.FragmentTextTaskBinding
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.ext.android.viewModel


class TextTaskFragment : Fragment() {

    lateinit var auth : FirebaseAuth

    private val taskViewModel : TaskViewModel by activityViewModels()
    private lateinit var binding: FragmentTextTaskBinding
    private lateinit var db : FirebaseDatabase
    private lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_task, container, false)
    }


}