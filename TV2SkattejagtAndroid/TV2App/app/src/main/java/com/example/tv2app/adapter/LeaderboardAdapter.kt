package com.example.tv2app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tv2app.R
import com.example.tv2app.models.User

class LeaderboardAdapter(private val userList : ArrayList<User?>) : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_element, parent, false)

        return LeaderboardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {

        // Top 3 players get a trophy badge
        when(position){
            0 ->holder.trophy.setImageResource(R.drawable.ic_baseline_star_24_gold)
            1 ->holder.trophy.setImageResource(R.drawable.ic_baseline_star_24_silver)
            2 ->holder.trophy.setImageResource(R.drawable.ic_baseline_star_24_bronze)
        }

        val currentItem = userList[position]
        holder.fullName.text = currentItem?.fullName ?:""
        holder.points.text = currentItem?.totalPoints.toString() ?:"0"
        holder.department.text = currentItem?.job ?:""
        holder.ranking.text = findIndex(userList, currentItem).toString()

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class LeaderboardViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val fullName : TextView = itemView.findViewById(R.id.ph_fullName2)
        val points : TextView = itemView.findViewById(R.id.score)
        val department : TextView = itemView.findViewById(R.id.department)
        val ranking : TextView = itemView.findViewById(R.id.rank)
        val trophy : ImageView = itemView.findViewById(R.id.trophy_icon)
    }

    private fun findIndex(array : ArrayList<User?>, item : User?) : Int {
        return array.indexOf(item) + 1

    }

    private fun updateUI(){

    }

}