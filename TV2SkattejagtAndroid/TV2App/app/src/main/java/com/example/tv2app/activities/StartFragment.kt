package com.example.tv2app.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.tv2app.R
import com.example.tv2app.databinding.ActivityMenuBinding
import com.example.tv2app.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth


/**
 * This is the first screen of the TV2 app. The user can choose between 3 buttons in a menu.
 */
class StartFragment : Fragment() {
    // Binding object instance with access to the views in the start_fragment.xml layout
    private lateinit var binding: FragmentStartBinding

    lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        return binding.root

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Binding Elements Here
        val signOut = binding.signOut
        
        //OnClickListeners
        signOut.setOnClickListener{ signOut() }


    }


    //TODO signOut() virker ikke i fragment. Move this to UserRepository
    private fun signOut(){
        auth.signOut()
        Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, LoginActivity::class.java) // Fragments is not of context type = need the parent activity
        activity?.startActivity(intent)
    }

    private fun goToLeaderboard(){
        findNavController().navigate(R.id.action_startFragment_to_leaderboardFragment)
    }




}