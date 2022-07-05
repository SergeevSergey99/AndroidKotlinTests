package com.example.retrofittest.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.FullBio
import com.example.retrofittest.MainActivity
import com.example.retrofittest.Model.Movie
import com.example.retrofittest.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_bio.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.image_movie

class MyMovieAdapter (private val context: Context, private var movieList: MutableList<Movie>) : RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>()
{
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.image_movie
        val txt_name: TextView = itemView.txt_name
        val txt_team: TextView = itemView.txt_team
        val txt_createdby: TextView = itemView.txt_createdby

        fun bind(listItem: Movie){
            image.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${listItem.name}", Toast.LENGTH_SHORT).show()
            }

            itemView.setOnClickListener {
                val act = Intent(it.context, FullBio::class.java)

                act.putExtra("imageurl",listItem.imageurl,)
                act.putExtra("name",listItem.name,)
                act.putExtra("realname",listItem.realname,)
                act.putExtra("createdby",listItem.createdby,)
                act.putExtra("publisher",listItem.publisher,)
                act.putExtra("first",listItem.firstappearance,)
                act.putExtra("bio",listItem.bio,)

                it.context.startActivity(act)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_layout, p0, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)

        Picasso.get().load(movieList[position].imageurl).into(holder.image)
        holder.txt_name.text = movieList[position].name
        holder.txt_team.text = movieList[position].team
        holder.txt_createdby.text = movieList[position].createdby
    }

    override fun getItemCount(): Int = movieList.size

}