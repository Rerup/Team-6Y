package com.example.tv2app.activities

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tv2app.R
import com.example.tv2app.adapter.LeaderboardAdapter
import com.example.tv2app.models.User
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardRecyclerView : RecyclerView
    private lateinit var userList : ArrayList<User?>
    lateinit var auth : FirebaseAuth

    private val userViewModel : UserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        leaderboardRecyclerView = findViewById(R.id.recyclerLeaderboard)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        leaderboardRecyclerView.setHasFixedSize(false)


        userList = getUserList()
        leaderboardRecyclerView.adapter = LeaderboardAdapter(userList)

        showCurrentPlayerData(userList)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)



    }

    override fun onPause() {
        super.onPause()
        userViewModel.userList.clear()
    }




    fun getUserList() : ArrayList<User?> {
        //TODO Spørg Steffen om dette, hvorfor er der duplicates her
        val list = userViewModel.inflateLeaderboard()
        //list.sortByDescending { it?.totalPoints ?:0 }
        list.sortBy { it?.totalPoints ?:0 }
        return list

    }

    fun showCurrentPlayerData(userList : ArrayList<User?>){
        //Track current User
        val currentUser = auth.currentUser?.uid ?:""

        //Get current user values with currently logged in userID.
        userViewModel.getCurrentUser(currentUser)

        //Fetches the user object so that we can populate the view.
        val userObject = userViewModel.fetchUserToView()

        //Binding view elements with corresponding values.
        val fullName : TextView = findViewById(R.id.cp_fullName)
        fullName.text = userObject?.fullName?:""

        val points : TextView = findViewById(R.id.cp_score)
        points.text = userObject?.totalPoints?.toString()?:"0"

        val department : TextView = findViewById(R.id.cp_department)
        department.text = userObject?.departmentId?:""

        val ranking : TextView = findViewById(R.id.cp_rank)
        ranking.text = findIndex(userList,userObject).toString()

    }

    //TODO Eventuelt flytte til UserViewModel, da metode defineres to steder (LeaderBoardAdapter)
    private fun findIndex(array : ArrayList<User?>, item : User?) : Int {
        return array.indexOf(item) + 1
    }


}