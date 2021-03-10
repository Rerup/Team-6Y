package com.example.tv2app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tv2app.activities.MenuActivity
import com.example.tv2app.activities.RegisterActivity
import com.example.tv2app.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    //Binding layout elements
    lateinit var binding: ActivityLoginBinding

    //Firebase Refs
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

            if (emailInput.text!!.trim().isNotEmpty() && passwordInput.text!!.trim().isNotEmpty()) {

                logInUser(emailInput.text!!.trim().toString(),
                        passwordInput.text!!.trim().toString())
            }
            else {

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
            Toast.makeText(applicationContext, "Welcome back", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    //Fun for userLogin
    private fun logInUser(email : String, password: String){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->

            if(task.isSuccessful){
                Log.d("AUTH", "Sign in successful")

                Toast.makeText(applicationContext,"Signed In", Toast.LENGTH_SHORT)
                        .show()
                //Sets current User
                val user = auth.currentUser

                //Go To Menu when signed in
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }

            else{
                //Throw User A Message if failed
                Log.d("AUTH", "Sign in failed" + task.exception)
                Toast.makeText(applicationContext, "Sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


}