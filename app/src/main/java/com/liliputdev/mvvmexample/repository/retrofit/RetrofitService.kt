package com.liliputdev.mvvmexample.repository.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

/**
 * Created by Masood Dalman on 6/26/2022.
 */
interface RetrofitService {

    companion object {
        private var retrofitFakeApiService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitFakeApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitFakeApiService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitFakeApiService!!
        }
    }

}