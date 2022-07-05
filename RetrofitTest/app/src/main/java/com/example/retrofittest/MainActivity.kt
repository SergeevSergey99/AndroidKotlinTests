package com.example.retrofittest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.Adapter.MyMovieAdapter
import com.example.retrofittest.Common.Common
import com.example.retrofittest.Interface.RetrofitServices
import kotlinx.android.synthetic.main.activity_main.*
import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittest.Model.Movie


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyMovieAdapter
    //lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        recycler.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager
        //dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()


    }

    private fun getAllMovieList() {
        //dialog.show()
        mService.getModelList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {

                adapter = MyMovieAdapter(baseContext, response.body() as MutableList<Movie>)
                adapter.notifyDataSetChanged()
                recycler.adapter = adapter


                //dialog.dismiss()
            }
        })
    }
}


