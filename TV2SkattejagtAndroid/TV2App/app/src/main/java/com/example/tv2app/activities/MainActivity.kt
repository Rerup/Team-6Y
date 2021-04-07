package com.example.tv2app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.tv2app.R
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    private val userViewModel: UserViewModel by viewModel()
    private val taskViewModel : TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set up navigation controller
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Get an instance of NavController from the NavHostFragment
        navController = navHostFragment.navController

        // Show a title in the app bar based off of the destination's label, and
        // display the Up(←) button whenever you're not on a top-level destination.
        setupActionBarWithNavController(navController)

        // Set up bottom navigation bar
        val hintFragment = HintFragment()
        val homeFragment = StartFragment()
        val leaderboardFragment = LeaderboardFragment()

        setCurrentFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.hint->setCurrentFragment(hintFragment)
                R.id.home->setCurrentFragment(homeFragment)
                R.id.leaderbord2->setCurrentFragment(leaderboardFragment)
            }
            true
        }

        //Populate DB with Tasks
        taskViewModel.dummyData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.indstillinger -> {
                Toast.makeText(this,"Indstillinger", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.logud -> {
                signOut()
                return true
            }

            R.id.oplysninger -> {
                goToProfile()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun signOut(){
        userViewModel.signOutUser()
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goToProfile(){
        navController.navigate(R.id.action_startFragment_to_profileFragment)
    }

    // Metode til bottom navigation baren
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.nav_host_fragment,fragment)
            commit()
        }



}