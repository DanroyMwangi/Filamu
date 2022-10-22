package com.movie.filamu

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URL


class SliderRecyclerViewAdapter(private val mList: ArrayList<Movie>) :
    RecyclerView.Adapter<SliderRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImg : ImageView = itemView.findViewById<ImageView>(R.id.movieImg)
        val movieTitle : TextView = itemView.findViewById<TextView>(R.id.movieTitleTV)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_card,viewGroup,false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = mList[position]


        // sets the image to the imageview from our itemHolder class
        viewHolder.movieImg.setImageResource(movie.image)
        // sets the text to the textview from our itemHolder class
        viewHolder.movieTitle.text = movie.title
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = mList.size

}
