package com.example.tv2app.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentProfileBinding
import com.example.tv2app.models.User
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    lateinit var auth : FirebaseAuth

    private val userViewModel: UserViewModel by viewModel()

    private lateinit var binding: FragmentProfileBinding
    private lateinit var db : FirebaseDatabase
    private lateinit var ref : DatabaseReference



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        auth = FirebaseAuth.getInstance()
        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        //Track current User
        val currentUser = auth.currentUser?.uid ?:""


        //Layout Elements
        //Show Profile according to current user.
        fun getCurrentUser(id : String) {

            db = FirebaseDatabase.getInstance()
            ref = db.reference.child("Users")
            ref.child(id).addListenerForSingleValueEvent(object : ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {

                    val user = snapshot
                    val currentUserObject = user.getValue(User::class.java)
                    binding.email = currentUserObject?.email ?:""
                    binding.id = currentUserObject?.uniqueId ?:""
                    binding.department = currentUserObject?.departmentId ?:""
                    binding.points = currentUserObject?.totalPoints ?:0
                    binding.fullName = currentUserObject?.fullName ?:""

                    Log.i("DB READ", "email: ${binding.email}, tv2id: ${binding.id}  department: ${binding.department}  totalPoints: ${binding.points}, Name: ${binding.fullName}")
                }

                override fun onCancelled(error: DatabaseError) {
                    //Handle the error
                    Log.i("DB READ", error.message)
                }
            })
        }

        getCurrentUser(currentUser)

        // Inflate the layout for this fragment
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}