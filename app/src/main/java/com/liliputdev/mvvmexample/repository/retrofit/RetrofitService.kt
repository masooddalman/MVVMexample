package com.liliputdev.mvvmexample.repository.retrofit

import android.util.JsonReader
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.APIModelAllProduct
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.ApiLoginModel
import com.liliputdev.mvvmexample.repository.retrofit.sendModel.SendModelLogin
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.http.GET


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