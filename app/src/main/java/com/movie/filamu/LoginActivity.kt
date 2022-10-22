package com.movie.filamu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Initialize views
        val loginEmailET = findViewById<TextInputEditText>(R.id.loginEmailET)
        val loginPassET = findViewById<TextInputEditText>(R.id.loginPassET)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        //Initialize Firebase Authentication
        val auth = FirebaseAuth.getInstance()

        //Create an button click listener
        loginBtn.setOnClickListener{
            val email = loginEmailET.text.toString()
            val password = loginPassET.text.toString()

            if(email.isNotBlank() && password.isNotBlank()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("userID",auth.currentUser)
                        startActivity(intent)
                    }
                    else(
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    )
                }
            }
            else{
                Toast.makeText(this,"Please fill in all the values to continue.",Toast.LENGTH_SHORT).show()
            }

        }
    }
}