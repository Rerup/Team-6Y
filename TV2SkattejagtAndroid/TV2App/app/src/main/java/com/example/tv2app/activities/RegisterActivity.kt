package com.example.tv2app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import com.example.tv2app.databinding.ActivityRegisterBinding
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {


    //Binding Layout Elements
    lateinit var binding: ActivityRegisterBinding

    //Firebase Ref
    lateinit var auth : FirebaseAuth

    private val userViewModel: UserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get FirebaseAuth Instance to track the user
        auth = FirebaseAuth.getInstance()

        //Binding to layout elements
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Layout
        val registerLogin = binding.registerButton
        val emailInput = binding.emailInput
        val passwordInput = binding.passwordInputRegister
        val confirmPassword = binding.confirmPassword
        val uniqueId = binding.uniqueId
        val fullName = binding.fullName
        val rbDigital = binding.rbDigitial
        val rbJournalist = binding.rbJournalist
        val rbBooster = binding.rbBooster
        val rbFyn = binding.rbFyn
        val rbKøbenhavn = binding.rbCopenhagen
        val departmentGroup = binding.radioGroupDepartment
        val jobGroup = binding.radioGroupJob


        //User created if success
        registerLogin.setOnClickListener {
            if (emailInput.text?.trim()?.isEmpty() == true || passwordInput.text?.trim()?.isEmpty() == true
               || confirmPassword.text?.trim()?.isEmpty() == true || uniqueId.text?.trim()?.isEmpty() == true || departmentGroup.isEmpty() || jobGroup.isEmpty() || fullName.text?.trim()?.isEmpty() == true
            ) {
                Toast.makeText(applicationContext, "Fill in all information", Toast.LENGTH_SHORT)
                        .show()

            }
            else if(passwordInput.text?.length ?:0 < 6)
            {
                Toast.makeText(applicationContext, "Password must at least contain 6 letters", Toast.LENGTH_SHORT)
                        .show()
            }
            else if (passwordInput.text.toString() != confirmPassword.text.toString()){
                Toast.makeText(applicationContext, "Passwords don't match", Toast.LENGTH_SHORT)
                        .show()
            }
            else {

                    userViewModel.createUser(emailInput.text?.trim().toString(), passwordInput.text?.trim().toString() , departmentGroup.checkedRadioButtonId.toString(),
                            uniqueId.text?.trim().toString(), fullName.text?.trim().toString())

                     //Makes sure that user is retrieved before trying to login
                     Thread.sleep(700)

                     if (auth.currentUser != null){

                         Toast.makeText(this, "Creating account and signing you in", Toast.LENGTH_SHORT).show()
                         startActivity((Intent(this, LoginActivity::class.java)))
                     }

                     else {
                         Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show()
                     }

            }

        }

    }

}