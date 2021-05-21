package com.example.tv2app.activities

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tv2app.R
import com.example.tv2app.adapter.LeaderboardAdapter
import com.example.tv2app.models.User
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardActivity : AppCompatActivity() {


    lateinit var auth : FirebaseAuth
    private val adapter = LeaderboardAdapter()

    private val userViewModel : UserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        auth = FirebaseAuth.getInstance()

        //Retrieve List of Users
        getUserList()

        //Shows the current rank of the logged in User.
        showCurrentPlayerData()

        //Initialize RecyclerView
        initRecyclerView()

        //Notify RecyclerView that data has changed since being initialized.
        recyclerLeaderboard.adapter?.notifyDataSetChanged()


    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        recyclerLeaderboard.adapter = adapter


        //Load data to view here
        userViewModel.userLiveData.observe(this) { userObject ->

            //Binding view elements with corresponding values.
            val fullName: TextView = findViewById(R.id.cp_fullName)
            fullName.text = userObject?.fullName ?: ""

            val points: TextView = findViewById(R.id.cp_score)
            points.text = userObject?.totalPoints?.toString() ?: "0"

            val department: TextView = findViewById(R.id.cp_department)
            department.text = userObject?.departmentId ?: ""

            //val ranking: TextView = findViewById(R.id.cp_rank)
            //ranking.text = userViewModel.findUserIndexInLeaderboard(userList, userObject).toString()
        }

        userViewModel.leaderboard.observe(this) {users ->
            adapter.updateUI(users)
        }

        return super.onCreateView(name, context, attrs)
    }

    private fun initRecyclerView(){
        //leaderboardRecyclerView = findViewById(R.id.recyclerLeaderboard)

    }

    override fun onPause() {
        super.onPause()
        userViewModel.userList.clear()
    }

    private fun getUserList()  {
        userViewModel.inflateLeaderboard()
    }

    private fun showCurrentPlayerData(){
        //Track current User
        val currentUser = auth.currentUser?.uid ?:""

        //Get current user values with currently logged in userID.
        userViewModel.getCurrentUser(currentUser)

        //Fetches the user object so that we can populate the view.
        val userObject = userViewModel.fetchUserToView()

    }



}