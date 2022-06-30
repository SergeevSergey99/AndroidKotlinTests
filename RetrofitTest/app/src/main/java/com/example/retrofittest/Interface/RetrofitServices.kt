package com.example.retrofittest.Interface

import com.example.retrofittest.Model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("marvel")
    fun getModelList() : Call<MutableList<Movie>>
}