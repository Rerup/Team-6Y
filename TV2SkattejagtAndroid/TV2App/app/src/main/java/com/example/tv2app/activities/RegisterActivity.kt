package com.example.tv2app.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.convertTo
import androidx.core.view.isEmpty
import com.example.tv2app.activities.LoginActivity
import com.example.tv2app.R
import com.example.tv2app.databinding.ActivityRegisterBinding
import com.example.tv2app.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

    class RegisterActivity : AppCompatActivity() {

    //Firebase Auth Ref
    private lateinit var auth : FirebaseAuth

    private lateinit var ref : DatabaseReference

    //Binding Layout Elements
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Firebase Auth Instance
        auth = FirebaseAuth.getInstance()

        //Firebase Database Instance
        ref = FirebaseDatabase.getInstance().getReference("Users")

        //Binding to layout elements
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Layout
        val registerLogin = binding.registerButton
        val emailInput = binding.emailInput
        val passwordInput = binding.passwordInputRegister
        val confirmPassword = binding.confirmPassword
        val uniqueId = binding.uniqueId
        val rbDigital = binding.rbDigitial
        val rbJournalist = binding.rbJournalist
        val rbBooster = binding.rbBooster
        val rbFyn = binding.rbFyn
        val rbKøbenhavn = binding.rbCopenhagen
        val departmentGroup = binding.radioGroupDepartment
        val jobGroup = binding.radioGroupJob



        //User created if success
        registerLogin.setOnClickListener {

            if (emailInput.text!!.trim().isEmpty() || passwordInput.text!!.trim().isEmpty()
               || confirmPassword.text!!.trim().isEmpty() || uniqueId.text!!.trim().isEmpty() ||departmentGroup.isEmpty() || jobGroup.isEmpty()
            ) {
                Toast.makeText(applicationContext, "Fill in all information", Toast.LENGTH_SHORT).show()

            }
            else if(passwordInput.text!!.length < 6)
            {
                Toast.makeText(applicationContext, "Password must at least contain 6 letters", Toast.LENGTH_SHORT)
                        .show()
            }
            else if (passwordInput.text.toString() != confirmPassword.text.toString()){
                Toast.makeText(applicationContext, "Password don't match", Toast.LENGTH_SHORT)
                        .show()
            }
            else {

                createUser(emailInput.text!!.trim().toString(), passwordInput.text!!.trim().toString(), departmentGroup.checkedRadioButtonId.toString(),
                        uniqueId.text!!.toString())

                Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT).show()

            }

        }


    }
        private fun createUser(email: String, password : String, department: String, id: String) {

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->

                if(task.isSuccessful) {

                    Log.e("AUTH", "Successful")

                    val newUserParams = User(totalPoints = 0, userIdDb = auth.currentUser.uid, departmentId = department, uniqueId = id)

                    //ref.child("User").setValue(newUserParams)
                    ref.child("User").setValue(newUserParams)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Log.e("AUTH", "Failed.." + task.exception)
                }
            }
        }


}