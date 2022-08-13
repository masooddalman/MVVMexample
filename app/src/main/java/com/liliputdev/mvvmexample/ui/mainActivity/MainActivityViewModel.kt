package com.liliputdev.mvvmexample.ui.mainActivity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.liliputdev.mvvmexample.R
import com.liliputdev.mvvmexample.repository.retrofit.RetrofitService
import com.liliputdev.mvvmexample.repository.retrofit.WebRepository
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.APIModelAllProduct
import com.liliputdev.mvvmexample.repository.retrofit.apiModel.APIModelAllProductElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Masood Dalman on 6/29/2022.
 */
class MainActivityViewModel(val context: Context):ViewModel() {
    val _retrofit= RetrofitService.getInstance()
    private var  repository: WebRepository = WebRepository(_retrofit)
    val listData=MutableLiveData<ArrayList<APIModelAllProductElement>>()

    fun getAllProduct()
    {
        val response=repository.getAllProduct()
        response.enqueue(object : Callback<APIModelAllProduct> {
            override fun onResponse(
                call: Call<APIModelAllProduct>,
                response: Response<APIModelAllProduct>
            ) {
                Log.v("masood","all product size: ${response.body()?.size}")
                listData.postValue(response.body())
                response.body()?.forEach {
                    Log.v("masood","item : ${it.title} - category:${it.category}")
                }
            }

            override fun onFailure(call: Call<APIModelAllProduct>, t: Throwable) {
                Log.v("masood","all product failed")
            }

        })
    }

    fun filterItems(category: String):ArrayList<APIModelAllProductElement>
    {
        if (category.isEmpty())
            return listData.value!!
        var result = listData.value?.filter { it->it.category==category} as ArrayList<APIModelAllProductElement>
        Log.v("masood","filtred item : ${result.size}")
        return result
    }

    fun sortItems(sort:String):ArrayList<APIModelAllProductElement>
    {
        if(sort.isEmpty())
            return listData.value!!
        val result=listData.value
        when (sort) {
            "name" -> {
                result?.sortBy { it.title }
            }
            else -> {}
        }
        return result!!
    }
}