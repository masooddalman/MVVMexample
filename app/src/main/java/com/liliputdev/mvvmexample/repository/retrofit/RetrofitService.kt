package com.liliputdev.mvvmexample.repository.retrofit

import com.liliputdev.mvvmexample.repository.retrofit.apiModel.APIModelAllProduct
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.ApiLoginModel
import com.liliputdev.mvvmexample.repository.retrofit.sendModel.SendModelLogin
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
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

    @POST("auth/login")
    fun login(@Body loginBody:SendModelLogin): Call<ApiLoginModel>

    @GET("products")
    fun getAllProduct():Call<APIModelAllProduct>

}