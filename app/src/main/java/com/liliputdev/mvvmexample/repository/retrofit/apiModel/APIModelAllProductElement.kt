package com.liliputdev.mvvmexample.repository.retrofit.apiModel

import com.google.gson.annotations.SerializedName

/**
 * Created by Masood Dalman on 6/29/2022.
 */
typealias APIModelAllProduct = ArrayList<APIModelAllProductElement>

data class APIModelAllProductElement (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Double,
    @SerializedName("description") val description : String,
    @SerializedName("category") val category : String,
    @SerializedName("image") val image : String,
    @SerializedName("rating") val rating : Rating,
    var isfaved: Boolean =true
)

data class Rating (

    @SerializedName("rate") val rate : Double,
    @SerializedName("count") val count : Int
)
