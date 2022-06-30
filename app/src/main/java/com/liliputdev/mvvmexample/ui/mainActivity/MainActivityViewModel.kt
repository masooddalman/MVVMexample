package com.liliputdev.mvvmexample.ui.mainActivity

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.repository.retrofit.RetrofitService
import com.liliputdev.mvvmexample.repository.retrofit.WebRepository
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.APIModelAllProduct
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Masood Dalman on 6/29/2022.
 */
class MainActivityViewModel(val context: Context):ViewModel() {
    val _retrofit= RetrofitService.getInstance()
    private var  repository: WebRepository = WebRepository(_retrofit)

    fun getAllProduct()
    {
        val response=repository.getAllProduct()
        response.enqueue(object : Callback<APIModelAllProduct> {
            override fun onResponse(
                call: Call<APIModelAllProduct>,
                response: Response<APIModelAllProduct>
            ) {
                Log.v("masood","all product size: ${response.body()?.size}")
            }

            override fun onFailure(call: Call<APIModelAllProduct>, t: Throwable) {
                Log.v("masood","all product failed")
            }

        });
    }
}