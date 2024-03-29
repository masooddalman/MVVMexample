package com.liliputdev.mvvmexample.ui.mainActivity

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liliputdev.mvvmexample.repository.database.FavDatabase
import com.liliputdev.mvvmexample.repository.database.dataModel.FavProduct
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
class MainActivityViewModel(val context: Context) : ViewModel() {
    val _retrofit = RetrofitService.getInstance()
    private var repository: WebRepository = WebRepository(_retrofit)
    val listData = MutableLiveData<ArrayList<APIModelAllProductElement>>()
    val favDb=FavDatabase.getInstance(context)

    fun getAllProduct() {
        val favProduct=getAllFavProduct()
        val response = repository.getAllProduct()
        response.enqueue(object : Callback<APIModelAllProduct> {
            override fun onResponse(
                call: Call<APIModelAllProduct>,
                response: Response<APIModelAllProduct>
            ) {
                Log.v("masood", "all product size: ${response.body()?.size}")
                response.body()?.forEach {
                    it.isfaved=isThisProductFaved(it.id,favProduct)
                }
                listData.postValue(response.body())
                response.body()?.forEach {
                    Log.v("masood", "item : ${it.title} - category:${it.category}")
                }
            }

            override fun onFailure(call: Call<APIModelAllProduct>, t: Throwable) {
                Log.v("masood", "all product failed")
            }

        })
    }

    fun filterItems(category: String): ArrayList<APIModelAllProductElement> {
        if (category.isEmpty())
            return listData.value!!
        var result =
            listData.value?.filter { it -> it.category == category } as ArrayList<APIModelAllProductElement>
        Log.v("masood", "filtred item : ${result.size}")
        return result
    }

    fun sortItems(sort: String, asc: Boolean): ArrayList<APIModelAllProductElement> {
        val result = listData.value
        when (sort) {
            "name" -> {
                if (asc)
                    result?.sortBy { it.title }
                else
                    result?.sortByDescending { it.title }
            }
            "price" -> {
                if (asc)
                    result?.sortBy { it.price }
                else
                    result?.sortByDescending { it.price }
            }
            "rate" -> {
                if (asc)
                    result?.sortBy { it.rating.rate }
                else
                    result?.sortByDescending { it.rating.rate }
            }
            else -> {// clear sort
                result?.sortBy { it.id }
            }
        }
        return result!!
    }

    fun getAllFavProduct(): List<FavProduct> {
        return favDb.favDao().getFavList()
    }

    private fun isThisProductFaved(id:Int,favProducts:List<FavProduct>):Boolean
    {
        var result=false
        favProducts.forEach {
            if(it.id==id)
            {
                result=it.isFaved
            }
        }
        return result
    }

    fun manageFav(model: APIModelAllProductElement)
    {
        if(model.isfaved)
        {
            //remove
            favDb.favDao().delete(FavProduct(model.id,true))
        }
        else
        {
            //add
            favDb.favDao().addNewFav(FavProduct(model.id,true))
        }
    }
}