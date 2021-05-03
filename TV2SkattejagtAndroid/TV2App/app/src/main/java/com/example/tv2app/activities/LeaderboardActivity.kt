package com.example.tv2app.activities

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tv2app.R
import com.example.tv2app.adapter.LeaderboardAdapter
import com.example.tv2app.models.User
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardRecyclerView : RecyclerView
    private lateinit var userList : ArrayList<User?>
    lateinit var auth : FirebaseAuth

    private val userViewModel : UserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)


        auth = FirebaseAuth.getInstance()

        leaderboardRecyclerView = findViewById(R.id.recyclerLeaderboard)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        leaderboardRecyclerView.setHasFixedSize(false)


        userList = getUserList()
        leaderboardRecyclerView.adapter = LeaderboardAdapter(userList)


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



}