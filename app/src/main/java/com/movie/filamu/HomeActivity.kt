package com.movie.filamu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Initialize the Recycler views here
        val newRV = findViewById<RecyclerView>(R.id.newRV)
        val featuredRV = findViewById<RecyclerView>(R.id.featuredRV)
        val hotRV = findViewById<RecyclerView>(R.id.hotRV)


        newRV.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        featuredRV.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        hotRV.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        //Initialize the image buttons here and give minimal functionality
        //TODO: Implement the activities related to these buttons
        val userImgBtn = findViewById<ImageButton>(R.id.accountBtn)
        val burgerMenu = findViewById<ImageButton>(R.id.burgerBtn)

        userImgBtn.setOnClickListener{
            Toast.makeText(this, intent.getStringExtra("userID"),Toast.LENGTH_SHORT).show()
        }
        burgerMenu.setOnClickListener{
            Toast.makeText(this, "Burger Menu Clicked",Toast.LENGTH_SHORT).show()
        }

        //Initialize adapter here and attach it.
        val blackAdam = Movie("Black Adam",R.drawable.black_adam)
        val entergalactic =  Movie("Entergalactic",R.drawable.entergalactic)
        val dayshift = Movie("Day Shift", R.drawable.day_shift)

        //Create a list to store these movies
        val mList : ArrayList<Movie> = ArrayList()
        mList.add(blackAdam)
        mList.add(entergalactic)
        mList.add(dayshift)
        mList.add(blackAdam)
        mList.add(entergalactic)
        mList.add(dayshift)

        // Initialize movie adapter
        val movieAdapter = SliderRecyclerViewAdapter(mList)

        //Attach the adpter to the rcycler view
        newRV.adapter = movieAdapter
        featuredRV.adapter = movieAdapter
        hotRV.adapter = movieAdapter
    }
}