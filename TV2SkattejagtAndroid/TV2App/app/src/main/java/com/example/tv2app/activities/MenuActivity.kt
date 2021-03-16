package com.example.tv2app.activities

/*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tv2app.databinding.ActivityMenuBinding
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {

    //Binding layout elements
    lateinit var binding: ActivityMenuBinding

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        //Binding Layout for easy access
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Binding Elements Here
        val signOut = binding.signOut

        //OnClickListeners
        signOut.setOnClickListener{
            signOut()
        }

    }

    //TODO Move this to UserRepository
    private fun signOut(){
        auth.signOut()
        Toast.makeText(applicationContext, "Signed Out", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}*/
