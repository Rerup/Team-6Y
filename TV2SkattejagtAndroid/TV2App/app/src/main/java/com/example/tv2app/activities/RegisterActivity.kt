package com.example.tv2app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.example.tv2app.databinding.ActivityRegisterBinding
import com.example.tv2app.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {


    //Binding Layout Elements
    lateinit var binding: ActivityRegisterBinding
    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Binding to layout elements
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Layout
        val registerLogin = binding.registerButton
        val emailInput = binding.emailInput //TODO ?:""
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
            //TODO Fix this shit
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
                Toast.makeText(applicationContext, "Passwords don't match", Toast.LENGTH_SHORT)
                        .show()
            }
            else {

                userViewModel.createUser(emailInput.text!!.trim().toString(), passwordInput.text!!.trim().toString(), departmentGroup.checkedRadioButtonId.toString(),
                    uniqueId.text!!.toString(), this)





            }

        }


    }


}