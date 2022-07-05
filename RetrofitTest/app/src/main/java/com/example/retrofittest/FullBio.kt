package com.example.retrofittest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_full_bio.*

class FullBio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_bio)

        Picasso.get().load(intent.getStringExtra("imageurl")).into(image_movie)

        name.text = intent.getStringExtra("name")
        realName.text = intent.getStringExtra("realname")
        createdby.text = intent.getStringExtra("createdby")
        publisher.text = intent.getStringExtra("publisher")
        firstapperance.text = intent.getStringExtra("first")
        bio.text = intent.getStringExtra("bio")
    }
}