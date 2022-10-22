package com.movie.filamu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Initializing the views(Components) in this activity
        //EditTexts initialization
        val regUname = findViewById<TextInputEditText>(R.id.regUnameET)
        val regEmail = findViewById<TextInputEditText>(R.id.regEmailET)
        val regPass =  findViewById<TextInputEditText>(R.id.regPassET)
        val regConPass = findViewById<TextInputEditText>(R.id.regConPassET)
        val regBtn = findViewById<Button>(R.id.regBtn)


        //Initialize Firebase
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        regBtn.setOnClickListener{
            val username = regUname.text.toString()
            val email = regEmail.text.toString()
            val pass = regPass.text.toString()
            val conPass = regConPass.text.toString()

            if(username.isNotBlank() && email.isNotBlank() && pass.isNotBlank() && conPass.isNotBlank()){
                if(conPass.equals(pass)){
                    val user = User(username=username,email=email)
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{it->
                        if (it.isSuccessful){
                            user.userID = auth.currentUser?.uid.toString()
                            firestore
                                .collection("users")
                                .add(user)
                                .addOnCompleteListener{it2->
                                    if (it2.isSuccessful){
                                        Toast.makeText(this,user.userID,Toast.LENGTH_LONG).show()
                                        val intent = Intent(this, HomeActivity::class.java)
                                        intent.putExtra("userID",user.userID)
                                        intent.putExtra("username",user.username)
                                        startActivity(intent)
                                    }
                                    else{
                                        Toast.makeText(this,it2.exception.toString(),Toast.LENGTH_LONG).show()
                                    }
                                }

                        }
                    }

                }
                else{
                    regConPass.error = "Password mismatch."
                }
            }
            else{
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}