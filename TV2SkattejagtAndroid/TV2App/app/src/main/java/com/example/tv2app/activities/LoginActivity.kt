package com.example.tv2app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tv2app.activities.RegisterActivity
import com.example.tv2app.databinding.ActivityLoginBinding
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class LoginActivity : AppCompatActivity() {

    //Binding layout elements
    lateinit var binding: ActivityLoginBinding

    //Initialize ViewModel
    private val userViewModel: UserViewModel by viewModel()


    //Firebase Ref
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()



        //Set Binding to this layout
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //View Elements
        val buttonLogin = binding.loginButton
        val emailInput = binding.emailInput
        val passwordInput = binding.passwordInput
        val createAccount = binding.createAccountTV

        //Listening for Login Button Click
        buttonLogin.setOnClickListener {

            //Checking if User typed in all information.
            if (emailInput.text?.trim()?.isNotEmpty() == true && passwordInput.text?.trim()?.isNotEmpty() == true) {

                userViewModel.loginUser(emailInput.text?.trim().toString(),
                        passwordInput.text?.trim().toString())


                //Makes sure that user is retrieved before trying to login in Activity
                Thread.sleep(700)

                if (auth.currentUser != null){
                    //Sign user into main menu.
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    userViewModel.userListener()


                }
                else {
                    Toast.makeText(this, "Failed to sign in", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                //Give Toast to User to fill in all information.
                Toast.makeText(applicationContext, "Type in All information", Toast.LENGTH_SHORT)
                        .show()
            }

        }

        //Register New Account Clicked, Opens RegisterActivity
        createAccount.setOnClickListener{
            val intent = Intent(this,
                    RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart(){
        super.onStart()
        //Check to see if User is already logged in, if so go to Main Menu.
        val currentUser = auth.currentUser
        if(currentUser != null)
        {
            Toast.makeText(applicationContext, "Welcome Back", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }



}

