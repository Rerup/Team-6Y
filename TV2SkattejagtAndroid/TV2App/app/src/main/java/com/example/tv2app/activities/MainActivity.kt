package com.example.tv2app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tv2app.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up navigation controller
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Get an instance of NavController from the NavHostFragment
        val navController = navHostFragment.navController

        // Show a title in the app bar based off of the destination's label, and
        // display the Up(←) button whenever you're not on a top-level destination.
        setupActionBarWithNavController(navController)
    }
}