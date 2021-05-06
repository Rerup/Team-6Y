package com.example.tv2app.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentProfileBinding
import com.example.tv2app.models.User
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    lateinit var auth : FirebaseAuth

    private val userViewModel: UserViewModel by viewModel()

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        auth = FirebaseAuth.getInstance()
        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        //Get Profile Data
        getProfileData()

        // Inflate the layout for this fragment
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Populate view with corresponding user values.
        showProfileData()

    }

    private fun getProfileData(){
        //Track current User
        val currentUser = auth.currentUser?.uid ?:""

        //Get current user values with currently logged in userID.
        userViewModel.getCurrentUser(currentUser)
    }

    private fun showProfileData() {

        //Fetches the user object so that we can populate the view.
        val userObject = userViewModel.fetchUserToView()

        //Binding view elements with corresponding values.
        binding.email = userObject?.email ?:""
        binding.id = userObject?.uniqueId ?:""
        binding.department = userObject?.departmentId ?:""
        binding.points = userObject?.totalPoints ?:0
        binding.fullName = userObject?.fullName ?:""
        binding.job = userObject?.job ?:""

        //Logging the data for testing
        Log.i("DB READ", "email: ${binding.email}, tv2id: ${binding.id}  department: ${binding.department}  totalPoints: ${binding.points}, Name: ${binding.fullName}, Job: ${binding.job}")
        Log.i("THREAD", "I finished now")




    }

}