package com.example.retrofittest.Common

import com.example.retrofittest.Interface.RetrofitServices
import com.example.retrofittest.Retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/marvel/"
    val retrofitService : RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}